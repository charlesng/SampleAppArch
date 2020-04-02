package com.cn29.aac.datasource.github.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.cn29.aac.repo.github.Contributor
import com.cn29.aac.repo.github.Repo
import com.cn29.aac.repo.github.RepoSearchResult
import com.cn29.aac.utils.LiveDataTestUtil
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
internal class RepoDaoTest {
    private lateinit var loadContributors: LiveData<List<Contributor>>
    private lateinit var loadRepoList: List<Repo>
    private lateinit var loadRepo: Repo

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var githubDb: GithubDb
    private lateinit var repoDao: RepoDao


    @Before
    fun setUp() {
        githubDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
                                                GithubDb::class.java).build()
        repoDao = githubDb.repoDao()
    }

    @After
    fun tearDown() {
        githubDb.close()
    }

    @Test
    fun should_insert_1_repo() = runBlocking {
        val repo = generateDummyRepo()
        givenRepoInserted(*repo.toTypedArray())
        whenLoadRepositories("charlesng0209")
        thenRepoListSizeShouldBe(1)
        thenRepoShouldBe(repo[0],
                         loadRepoList[0])
    }

    @Test
    fun should_insert_1_repo_empty_desc() = runBlocking {
        val repo = generateDummyRepo(description = null)
        givenRepoInserted(*repo.toTypedArray())
        whenLoadRepositories("charlesng0209")
        thenRepoListSizeShouldBe(1)
        thenRepoShouldBe(repo[0],
                         loadRepoList[0])
    }

    @Test
    fun should_insert_1_contributor() {
        val repo = generateDummyRepo()
        givenRepoInserted(*repo.toTypedArray())
        val contributor = generateDummyContributor()
        givenContributorsInserted(listOf(contributor))
        whenContributorsLoaded("charlesng0209", "Testing1")
        thenContributorListSizeShouldBe(1)
    }

    @Test
    fun should_insert_repos_list() = runBlocking {
        val repo = generateDummyRepo(3)
        givenRepoInserted(*repo.toTypedArray())
        whenLoadRepositories("charlesng0209")
        thenRepoListSizeShouldBe(3)
    }


    @Test
    fun should_load_repo_with_login_and_name() = runBlocking {
        val repo = generateDummyRepo(1)
        givenRepoInserted(*repo.toTypedArray())
        whenLoad(login = "charlesng0209", name = "Testing1")
        thenRepoShouldBe(expect = repo[0],
                         actual = loadRepo)
    }

    @Test
    fun should_insert_searchResult() {
        val result = generateDummyRepoSearchResult()
        whenRepoSearchResult(result = result)
    }

    @Test
    fun should_return_2_repos() = runBlocking {
        val repos = generateDummyRepo(3)
        givenRepoInserted(*repos.toTypedArray())
        whenRepoLoadById(1, 2, 3)
        thenRepoListSizeShouldBe(3)
    }

    private suspend fun whenRepoLoadById(vararg repoIds: Int) {
        loadRepoList = repoDao.loadById(repoIds.toList())
    }

    private fun whenRepoSearchResult(result: RepoSearchResult) {
        repoDao.insert(result)
    }

    private fun generateDummyRepoSearchResult(): RepoSearchResult =
            RepoSearchResult(query = "testingQuery",
                             repoIds = listOf(1, 2, 3, 4),
                             totalCount = 4,
                             next = 3)

    private suspend fun whenLoad(login: String,
                                 name: String) {
        loadRepo = repoDao.load(login, name)
    }

    private fun thenContributorListSizeShouldBe(size: Int) {
        assertEquals(size, LiveDataTestUtil.getValue(loadContributors).size)
    }

    private fun whenContributorsLoaded(login: String,
                                       name: String) {
        loadContributors = repoDao.loadContributors(login, name)
    }

    private fun givenContributorsInserted(list: List<Contributor>
    ) {
        repoDao.insertContributors(list)
    }


    private fun thenRepoShouldBe(expect: Repo,
                                 actual: Repo
    ) {
        assertEquals(expect, actual)
    }

    private fun thenRepoListSizeShouldBe(size: Int) {
        assertEquals(size, loadRepoList.size)
    }

    private suspend fun whenLoadRepositories(owner: String) {
        this.loadRepoList = repoDao.loadRepositories(owner = owner)
    }

    private fun givenRepoInserted(vararg repo: Repo?) {
        repoDao.insert(*repo)
    }

    private fun generateDummyContributor(): Contributor {
        return Contributor(login = "charlesng0209",
                           contributions = 1,
                           avatarUrl = "",
                           repoName = "Testing1",
                           repoOwner = "charlesng0209")
    }

    private fun generateDummyRepo(size: Int = 1,
                                  description: String? = "Testing Repo"
    ): List<Repo> = (1..size).map {
        Repo(id = it,
             name = "Testing$it",
             fullName = "Testing Github Repo$it",
             description = description,
             owner = Repo.Owner(login = "charlesng0209",
                                url = "www.google.com"),
             stars = 1)
    }
}