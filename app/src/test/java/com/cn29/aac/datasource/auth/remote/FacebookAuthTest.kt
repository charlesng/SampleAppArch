package com.cn29.aac.datasource.auth.remote

import com.cn29.aac.util.InstantExecutorExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class FacebookAuthTest {
    private lateinit var faceBookAuth: FacebookAuth

    @BeforeEach
    internal fun setUp() {
        faceBookAuth = FacebookAuth()
    }

    @Test
    fun `should set login status as 1 and authtype as facebook`() {
        val email = "charlesng0209@gmail.com"
        val liveData = faceBookAuth.getLogin(email)
        assertEquals(1, liveData.value?.body?.isLogin)
        assertEquals("Facebook", liveData.value?.body?.authType)
    }
}