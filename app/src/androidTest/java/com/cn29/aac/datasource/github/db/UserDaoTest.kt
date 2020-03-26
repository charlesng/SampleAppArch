package com.cn29.aac.datasource.github.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.cn29.aac.repo.github.User
import com.cn29.aac.utils.LiveDataTestUtil
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
internal class UserDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var githubDb: GithubDb
    private lateinit var userDao: UserDao
    private lateinit var findByLoginLiveData: LiveData<User>

    @Before
    fun setUp() {
        githubDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
                                                GithubDb::class.java).build()
        userDao = githubDb.userDao()
    }

    @After
    fun tearDown() {
        githubDb.close()
    }

    @Test
    fun should_insert_1_user_and_fetch() {
        val user = generateDummyUser()
        givenUserInserted(user = user)
        whenUserFetchByLogin(login = "charlesng0209")
        thenShouldUserFetch(expect = user,
                            actual = LiveDataTestUtil.getValue(
                                    findByLoginLiveData))
    }

    private fun thenShouldUserFetch(expect: User,
                                    actual: User) {
        assertEquals(expect, actual)
    }

    private fun whenUserFetchByLogin(login: String) {
        findByLoginLiveData = userDao.findByLogin(login)
    }

    private fun givenUserInserted(user: User) {
        userDao.insert(user)
    }

    private fun generateDummyUser(): User = User(login = "charlesng0209",
                                                 avatarUrl = "www.google.com",
                                                 name = "Long Ranger",
                                                 company = "MyCompany",
                                                 reposUrl = "www.google.com",
                                                 blog = "N/A")
}