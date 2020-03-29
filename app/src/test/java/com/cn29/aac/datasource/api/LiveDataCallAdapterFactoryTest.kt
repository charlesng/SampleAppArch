package com.cn29.aac.datasource.api

import androidx.lifecycle.LiveData
import com.cn29.aac.repo.github.Repo
import com.cn29.aac.util.InstantExecutorExtension
import com.google.gson.reflect.TypeToken
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


@ExtendWith(InstantExecutorExtension::class)
internal class LiveDataCallAdapterFactoryTest {
    private val factory: LiveDataCallAdapterFactory = LiveDataCallAdapterFactory()
    private lateinit var mockWebServer: MockWebServer
    private val NO_ANNOTATIONS = emptyArray<Annotation>()
    private lateinit var retrofit: Retrofit

    @BeforeEach
    internal fun setUp() {
        mockWebServer = MockWebServer()
        retrofit = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(factory)
                .build()
    }

    @Test
    fun `should get the data type without live data wrapper from the call adaptor `() {
        val returnType: Type = object :
                TypeToken<LiveData<ApiResponse<List<Repo>>>>() {}.type
        val responseType = factory.get(returnType, NO_ANNOTATIONS, retrofit)
                ?.responseType()
        assertEquals(object : TypeToken<List<Repo>>() {}.type, responseType)
    }
}