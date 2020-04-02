package com.cn29.aac.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    object Loading : Result<Nothing>()
    data class Error<T>(val message: String) : Result<T>()
    object Finish : Result<Nothing>()
}

fun <T, A> repositoryLiveData(localResult: (suspend () -> T?)? = null,
                              remoteResult: (suspend () -> Result<A>?)? = null,
                              saveFetchResult: suspend (A) -> Unit = { },
                              dispatcher: CoroutineDispatcher = Dispatchers.IO,
                              shouldFetch: (localData: T?) -> Boolean = { true }
): LiveData<Result<T>> =
        liveData(dispatcher) {
            emit(Result.Loading)
            val localData = localResult?.invoke()
            localData?.also {
                emit(Result.Success(it))
            }
            try {
                if (shouldFetch.invoke(localData)) {
                    remoteResult?.let {
                        when (val response = it.invoke()) {
                            is Result.Success -> {
                                saveFetchResult(response.data)
                            }
                            is Result.Error -> {
                                emit(Result.Error<T>(response.message))
                                localData?.also { data ->
                                    emit(Result.Success(data))
                                }
                            }
                            else -> {
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                emit(Result.Error<T>(e.message.toString()))
                localData?.also {
                    emit(Result.Success(it))
                }
            } finally {
                emit(Result.Finish)
            }
        }

suspend fun <T> transformResult(call: suspend () -> Response<T>): Result<T> {
    try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return Result.Success(body)
        }
        return error(" ${response.code()} ${response.message()}")
    } catch (e: Exception) {
        return error(e.message ?: e.toString())
    }
}

fun <T> error(message: String): Result<T> {
    return Result.Error("Network call has failed for a following reason: $message")
}
