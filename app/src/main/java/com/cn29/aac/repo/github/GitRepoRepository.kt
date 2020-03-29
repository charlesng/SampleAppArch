package com.cn29.aac.repo.github

import androidx.lifecycle.LiveData
import com.cn29.aac.AppExecutors
import com.cn29.aac.datasource.api.ApiResponse
import com.cn29.aac.datasource.github.db.RepoDao
import com.cn29.aac.datasource.github.remote.GithubService
import com.cn29.aac.repo.util.NetworkBoundResource
import com.cn29.aac.repo.util.RateLimiter
import com.cn29.aac.repo.util.Resource
import com.cn29.aac.util.Result
import com.cn29.aac.util.repositoryLiveData
import com.cn29.aac.util.transformResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Charles Ng on 3/10/2017.
 */
@Singleton
class GitRepoRepository @Inject constructor(private val appExecutors: AppExecutors,
                                            private val repoDao: RepoDao,
                                            private val githubService: GithubService,
                                            private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
                                            private val repoListRateLimit: RateLimiter<String> = RateLimiter(
                                                    10,
                                                    TimeUnit.MINUTES)
) {

    fun loadRepo(owner: String
    ): LiveData<Result<List<Repo>>> = repositoryLiveData(
            localResult = { repoDao.loadRepositories(owner) },
            remoteResult = {
                transformResult { githubService.getRepo(owner) }.apply {
                    if (this is Result.Error) {
                        repoListRateLimit.reset(owner)
                    }
                }
            },
            shouldFetch = { repoListRateLimit.shouldFetch(owner) },
            saveFetchResult = { repoDao.insertRepos(it) },
            dispatcher = this.dispatcher
    )

    fun loadRepo(owner: String?,
                 name: String?): LiveData<Resource<Repo>> {
        return object : NetworkBoundResource<Repo, Repo>(appExecutors) {
            override fun saveCallResult(item: Repo) {
                repoDao.insert(item)
            }

            override fun shouldFetch(data: Repo?): Boolean {
                return data == null
            }

            override fun loadFromDb(): LiveData<Repo> {
                return repoDao.load(owner, name)
            }

            override fun createCall(): LiveData<ApiResponse<Repo>> {
                return githubService.getRepo(owner, name)
            }
        }.asLiveData()
    }

}