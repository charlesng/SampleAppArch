package com.cn29.aac.datasource.api

import okhttp3.Headers.Companion.toHeaders
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import retrofit2.Response


internal class ApiResponseTest {
    private lateinit var response: ApiResponse<Void>

    companion object {
        @JvmStatic
        fun constructorArgument(): List<Arguments> =
                listOf(
                        Arguments.of(ApiResponse<String>(Response.success("success")),
                                     true),
                        Arguments.of(ApiResponse<String>(Response.success("null")),
                                     true),
                        Arguments.of(ApiResponse<String>(Throwable("Error")),
                                     false)
                )

        @JvmStatic
        fun getCodeArgument(): List<Arguments> =
                listOf(
                        Arguments.of(ApiResponse<String>(Response.success("null")),
                                     200),
                        Arguments.of(ApiResponse<String>(Throwable("Error")),
                                     500)
                )

    }

    @ParameterizedTest(name = "{0} return {1} status code")
    @MethodSource("getCodeArgument")
    fun `should return status code`(response: ApiResponse<String>,
                                    code: Int) {
        assertEquals(code, response.code)
    }

    @Test
    fun `should return links if 1 link is found in headers from github response`() {
        val link = "/api.github.com/user/7378196/repos?page=2>; rel=\"next\",  /api.github.com/user/7378196/repos?page=2>; rel=\"last\""
        val apiResponse = ApiResponse<String>(Response.success("",
                                                               mapOf("link" to link).toHeaders()))
        assertEquals(mapOf("next" to "/api.github.com/user/7378196/repos?page=2",
                           "last" to "/api.github.com/user/7378196/repos?page=2"),
                     apiResponse.links)
    }

    @ParameterizedTest(name = "{0} return {1} successful status")
    @MethodSource("constructorArgument")
    fun `should return correct boolean value for isSuccessful`(response: ApiResponse<String>,
                                                               isSuccessFul: Boolean) {
        assertEquals(isSuccessFul, response.isSuccessful)
    }

    @Test
    fun `should return page 2 from the response`() {
        val link = "/api.github.com/user/7378196/repos?page=2>; rel=\"next\",  /api.github.com/user/7378196/repos?page=2>; rel=\"last\""
        val apiResponse = ApiResponse<String>(Response.success("",
                                                               mapOf("link" to link).toHeaders()))
        assertEquals(2, apiResponse.nextPage)
    }

    @Test
    fun `should return null from the response if the page is not valid`() {
        val link = "/api.github.com/user/7378196/repos?page=>; rel=\"next\",  /api.github.com/user/7378196/repos?page=>; rel=\"last\""
        val apiResponse = ApiResponse<String>(Response.success("",
                                                               mapOf("link" to link).toHeaders()))
        assertEquals(null, apiResponse.nextPage)
    }
}