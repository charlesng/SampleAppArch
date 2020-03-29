package com.cn29.aac.datasource.auth.remote

import com.cn29.aac.util.InstantExecutorExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class GoogleAuthTest {
    private lateinit var googleAuth: GoogleAuth

    @BeforeEach
    internal fun setUp() {
        googleAuth = GoogleAuth()
    }

    @Test
    fun `should set login status as 1 and authtype as google`() {
        val email = "charlesng0209@gmail.com"
        val liveData = googleAuth.getLogin(email)
        assertEquals(1, liveData.value?.body?.isLogin)
        assertEquals("Google", liveData.value?.body?.authType)
    }
}