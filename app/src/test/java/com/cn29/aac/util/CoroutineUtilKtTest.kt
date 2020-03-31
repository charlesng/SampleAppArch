package com.cn29.aac.util

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import org.mockito.BDDMockito

interface Delegation {
    suspend fun remoteResult(): Result<String>
    suspend fun saveResult(s: String)
    fun localResult(): MutableLiveData<String>
    fun shouldFetch(): Boolean
}

fun <T> givenSuspended(block: suspend () -> T) = BDDMockito.given(runBlocking { block() })

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class CoroutineUtilKtTest {
    // Set the main coroutines dispatcher for unit testing
    companion object {
        @JvmField
        @RegisterExtension
        var coroutinesRule = CoroutinesTestExtension()
    }

    val delegation: Delegation = mock()
    private val LOCAL_RESULT = "Local Result Fetch"
    private val REMOTE_RESULT = "Remote Result Fetch"
    private val REMOTE_CRASH = "Remote Result Crash"

    @BeforeEach
    fun setUp() {
        given { delegation.shouldFetch() }
                .willReturn(true)
        given { delegation.localResult() }
                .willReturn(MutableLiveData(LOCAL_RESULT))
        givenSuspended { delegation.remoteResult() }
                .willReturn(Result.Success(REMOTE_RESULT))
    }

    @Test
    fun `should call local result only if the remote result should not fetch`() = coroutinesRule.runBlocking {
        //given
        given { delegation.shouldFetch() }.willReturn(false)

        //when
        repositoryLiveData<String, String>(
                localResult = { delegation.localResult() },
                remoteResult = { delegation.remoteResult() },
                shouldFetch = { delegation.shouldFetch() },
                dispatcher = coroutinesRule.dispatcher
        ).getOrAwaitValue()
        //then
        verify(delegation, times(1)).localResult()
        verify(delegation, never()).remoteResult()
    }


    @Test
    fun `should call remote result and then save result`() = coroutinesRule.runBlocking {
        //when
        repositoryLiveData<String, String>(
                shouldFetch = { delegation.shouldFetch() },
                remoteResult = { delegation.remoteResult() },
                saveFetchResult = { s -> delegation.saveResult(s) },
                dispatcher = coroutinesRule.dispatcher
        ).getOrAwaitValue()
        //then
        verify(delegation, times(1)).remoteResult()
        verify(delegation,
               times(1)).saveResult(REMOTE_RESULT)
    }

    @Test
    fun `should emit Loading, Success, Finish Status when we fetch local and then remote`() = coroutinesRule.runBlocking {
        //when
        val ld = repositoryLiveData<String, String>(
                localResult = { delegation.localResult() },
                shouldFetch = { delegation.shouldFetch() },
                remoteResult = { delegation.remoteResult() },
                saveFetchResult = { delegation.shouldFetch() },
                dispatcher = coroutinesRule.dispatcher
        )
        //then
        ld.captureValues {
            assertEquals(arrayListOf(Result.Loading,
                                     Result.Success(LOCAL_RESULT),
                                     Result.Finish), values)
        }
    }

    @Test
    fun `should emit Loading,Success, Error, Success, Finish Status when we fetch remote but fail`() = coroutinesRule.runBlocking {
        givenSuspended { delegation.remoteResult() }
                .willThrow(RuntimeException(REMOTE_CRASH))
        //when
        val ld = repositoryLiveData<String, String>(
                localResult = { delegation.localResult() },
                shouldFetch = { delegation.shouldFetch() },
                remoteResult = { delegation.remoteResult() },
                saveFetchResult = { delegation.shouldFetch() },
                dispatcher = coroutinesRule.dispatcher
        )
        //then
        ld.captureValues {
            assertEquals(arrayListOf(Result.Loading,
                                     Result.Success(LOCAL_RESULT),
                                     Result.Error(REMOTE_CRASH),
                                     Result.Success(LOCAL_RESULT),
                                     Result.Finish
            ), values)
        }
    }


}