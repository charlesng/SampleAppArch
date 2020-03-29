package com.cn29.aac.util

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import org.mockito.Mockito.*

class TestRepositoryLiveData {
    suspend fun remoteResult(): Result<String> = Result.Success("Testing")
    suspend fun saveResult(s: String): Unit = Unit
    fun localResult(): MutableLiveData<String> = MutableLiveData<String>()
    fun shouldFetch(): Boolean = true
}

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
internal class CoroutineUtilKtTest {
    // Set the main coroutines dispatcher for unit testing
    companion object {
        @JvmField
        @RegisterExtension
        var coroutinesRule = CoroutinesTestExtension()
    }


    private lateinit var testRepositoryLiveData: TestRepositoryLiveData


    @BeforeEach
    fun setUp() {
        testRepositoryLiveData = mock(TestRepositoryLiveData::class.java)
    }

    @Test
    internal fun `should call local result only`() = coroutinesRule.runBlocking {
        //given
        //when
        repositoryLiveData<String, Unit>(
                localResult = { testRepositoryLiveData.localResult() },
                shouldFetch = { testRepositoryLiveData.shouldFetch() },
                dispatcher = coroutinesRule.dispatcher
        ).getOrAwaitValue()
        //then
        verify(testRepositoryLiveData, times(1)).localResult()
        verify(testRepositoryLiveData, never()).shouldFetch()
    }

    @Test
    fun `should call remote result and then save result`() = coroutinesRule.runBlocking {
        //given
        `when`(testRepositoryLiveData.remoteResult()).thenReturn(Result.Success(
                "Testing"))
        `when`(testRepositoryLiveData.shouldFetch()).thenReturn(true)
        //when
        repositoryLiveData<String, String>(
                shouldFetch = { testRepositoryLiveData.shouldFetch() },
                remoteResult = { testRepositoryLiveData.remoteResult() },
                saveFetchResult = { s -> testRepositoryLiveData.saveResult(s) },
                dispatcher = coroutinesRule.dispatcher
        ).getOrAwaitValue()
        //then
        verify(testRepositoryLiveData, times(1)).remoteResult()
        verify(testRepositoryLiveData,
               times(1)).saveResult("Testing")
    }


}