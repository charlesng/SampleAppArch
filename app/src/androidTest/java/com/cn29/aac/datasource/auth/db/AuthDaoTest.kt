package com.cn29.aac.datasource.auth.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.cn29.aac.repo.user.LoginBean
import com.cn29.aac.utils.LiveDataTestUtil
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
internal class AuthDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var authDb: AuthDb
    private lateinit var authDao: AuthDao
    private lateinit var getLoginLiveData: LiveData<LoginBean>

    @Before
    fun setUp() {
        authDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
                                              AuthDb::class.java).build()
        authDao = authDb.authDao()
    }

    @After
    fun tearDown() {
        authDb.close()
    }

    @Test
    fun should_insert_login_and_fetch() {
        val loginBean = generateDummyLoginBean()
        givenLoginBeanInserted(loginBean)
        whenGetLoginByEmail(email = "charlesng0209@gmail.com")
        thenFetchedLoginBeanShouldBe(expect = loginBean,
                                     actual = LiveDataTestUtil.getValue(
                                             getLoginLiveData))
    }

    private fun thenFetchedLoginBeanShouldBe(expect: LoginBean,
                                             actual: LoginBean) {
        assertEquals(expect, actual)
    }

    private fun whenGetLoginByEmail(email: String) {
        getLoginLiveData = authDao.getLogin(email)
    }

    private fun givenLoginBeanInserted(loginBean: LoginBean) {
        authDao.insert(loginBean)
    }

    private fun generateDummyLoginBean(): LoginBean =
            LoginBean(authType = "Google", email = "charlesng0209@gmail.com")
}