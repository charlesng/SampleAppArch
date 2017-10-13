package com.cn29.aac.repo.github;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.cn29.aac.AppExecutors;
import com.cn29.aac.datasource.api.ApiResponse;
import com.cn29.aac.datasource.github.db.GithubDb;
import com.cn29.aac.datasource.github.db.RepoDao;
import com.cn29.aac.datasource.github.remote.GithubService;
import com.cn29.aac.repo.util.NetworkBoundResource;
import com.cn29.aac.repo.util.RateLimiter;
import com.cn29.aac.repo.util.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Charles Ng on 3/10/2017.
 */


@Singleton
public class GitRepoRepository {

  private GithubDb db;

  private GithubService githubService;

  private RepoDao repoDao;

  private AppExecutors appExecutors;

  private RateLimiter<String> repoListRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);

  @Inject
  public GitRepoRepository(AppExecutors appExecutors, GithubDb db, RepoDao repoDao,
      GithubService githubService) {
    this.db = db;
    this.repoDao = repoDao;
    this.githubService = githubService;
    this.appExecutors = appExecutors;
  }

  public LiveData<Resource<List<Repo>>> loadRepos(String owner) {
    return new NetworkBoundResource<List<Repo>, List<Repo>>(appExecutors) {
      @Override
      protected void saveCallResult(@NonNull List<Repo> item) {
        repoDao.insertRepos(item);
      }

      @Override
      protected boolean shouldFetch(@Nullable List<Repo> data) {
        return data == null || data.isEmpty() || repoListRateLimit.shouldFetch(owner);
      }

      @NonNull
      @Override
      protected LiveData<List<Repo>> loadFromDb() {
        return repoDao.loadRepositories(owner);
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<List<Repo>>> createCall() {
        return githubService.getRepos(owner);
      }

      @Override
      protected void onFetchFailed() {
        repoListRateLimit.reset(owner);
      }
    }.asLiveData();
  }

  public LiveData<Resource<Repo>> loadRepo(String owner, String name) {
    return new NetworkBoundResource<Repo, Repo>(appExecutors) {
      @Override
      protected void saveCallResult(@NonNull Repo item) {
        repoDao.insert(item);
      }

      @Override
      protected boolean shouldFetch(@Nullable Repo data) {
        return data == null;
      }

      @NonNull
      @Override
      protected LiveData<Repo> loadFromDb() {
        return repoDao.load(owner, name);
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<Repo>> createCall() {
        return githubService.getRepo(owner, name);
      }
    }.asLiveData();
  }

}
