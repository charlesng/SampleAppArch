package com.cn29.aac.datasource.auth.remote

import com.cn29.aac.utils.InstantExecutorExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class MyCompanyAuthTest {

    private lateinit var myCompanyAuth: MyCompanyAuth

    @BeforeEach
    fun setUp() {
        myCompanyAuth = MyCompanyAuth()
    }

    @Test
    fun `should set login status as 1 and authtype as MyCompany`() {
        val liveData = myCompanyAuth.getLogin("charlesng0209@gmail.com")
        assertEquals(1, liveData.value?.body?.isLogin)
        assertEquals("MyCompany", liveData.value?.body?.authType)
    }
}