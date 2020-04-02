package com.cn29.aac.datasource.auth.remote

import com.cn29.aac.repo.user.LoginBean
import com.cn29.aac.util.BaseCoroutineUtilKtTest
import com.cn29.aac.util.Result
import com.cn29.aac.util.runBlocking
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

val email = "charlesng0209@gmail.com"
val password = "MyPassword"

@ExperimentalCoroutinesApi
class MyCompanyAuthTest : BaseCoroutineUtilKtTest() {

    private lateinit var myCompanyAuth: MyCompanyAuth

    @BeforeEach
    fun setUp() {
        myCompanyAuth = MyCompanyAuth(coroutinesRule.dispatcher)
    }

    @Test
    fun `should set login status as 1 and authtype as MyCompany`() = coroutinesRule.runBlocking {
        val liveData: Result.Success<LoginBean> = myCompanyAuth.getLogin(email,
                                                                         password) as Result.Success<LoginBean>
        assertEquals(1, liveData.data.isLogin)
        assertEquals("MyCompany", liveData.data.authType)
    }
}


@ExperimentalCoroutinesApi
class FacebookAuthTest : BaseCoroutineUtilKtTest() {
    private lateinit var faceBookAuth: FacebookAuth

    @BeforeEach
    fun setUp() {
        faceBookAuth = FacebookAuth(coroutinesRule.dispatcher)
    }

    @Test
    fun `should set login status as 1 and authtype as facebook`() = coroutinesRule.runBlocking {
        val liveData: Result.Success<LoginBean> = faceBookAuth.getLogin(email,
                                                                        password) as Result.Success<LoginBean>
        assertEquals(1, liveData.data.isLogin)
        assertEquals("Facebook", liveData.data.authType)
    }
}


@ExperimentalCoroutinesApi
class GoogleAuthTest : BaseCoroutineUtilKtTest() {
    private lateinit var googleAuth: GoogleAuth

    @BeforeEach
    fun setUp() {
        googleAuth = GoogleAuth(coroutinesRule.dispatcher)
    }

    @Test
    fun `should set login status as 1 and authtype as google`() = coroutinesRule.runBlocking {
        val liveData: Result.Success<LoginBean> = googleAuth.getLogin(email,
                                                                      password) as Result.Success<LoginBean>
        assertEquals(1, liveData.data.isLogin)
        assertEquals("Google", liveData.data.authType)
    }
}