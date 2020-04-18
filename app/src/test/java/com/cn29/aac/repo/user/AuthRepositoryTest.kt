package com.cn29.aac.repo.user

import android.content.SharedPreferences
import com.cn29.aac.datasource.auth.db.AuthDao
import com.cn29.aac.datasource.auth.remote.FacebookAuth
import com.cn29.aac.datasource.auth.remote.GoogleAuth
import com.cn29.aac.datasource.auth.remote.MyCompanyAuth
import com.cn29.aac.util.BaseCoroutineUtilKtTest
import com.cn29.aac.util.getOrAwaitValue
import com.cn29.aac.util.givenSuspended
import com.cn29.aac.util.runBlocking
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString

val email = "charlesng0209@gmail.com"
val password = "Mypassword"

@ExperimentalCoroutinesApi
class AuthRepositoryTest : BaseCoroutineUtilKtTest() {

    lateinit var authRepository: AuthRepository

    val sharedPreferences = mock<SharedPreferences>()
    val authDao = mock<AuthDao>()
    val googleAuth = mock<GoogleAuth>()
    val facebookAuth = mock<FacebookAuth>()
    val myCompanyAuth = mock<MyCompanyAuth>()

    @BeforeEach
    fun setUp() {
        authRepository = AuthRepository(
                sharedPreferences = sharedPreferences,
                authDao = authDao,
                googleAuth = googleAuth,
                facebookAuth = facebookAuth,
                myCompanyAuth = myCompanyAuth,
                dispatcher = coroutinesRule.dispatcher
        )
    }

    @Test
    fun `should call the google auth if the google auth mode is selected`() = coroutinesRule.runBlocking {
        //given
        givenSuspended { authDao.getLogin(anyString()) }
                .willReturn(LoginBean(email,
                                      AuthRepository.AuthMode.GOOGLE.name,
                                      0,
                                      password))
        val mode = AuthRepository.AuthMode.GOOGLE
        //when
        authRepository.login(mode, email, password).getOrAwaitValue()
        //then
        verify(googleAuth, times(1)).getLogin(anyString(), anyString())
    }


}