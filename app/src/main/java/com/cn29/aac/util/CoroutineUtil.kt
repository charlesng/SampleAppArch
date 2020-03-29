package com.cn29.aac.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    object Loading : Result<Nothing>()
    data class Error<T>(val message: String) : Result<T>()
    object Finish : Result<Nothing>()
}


fun <T, A> repositoryLiveData(localResult: (() -> LiveData<T>) = { MutableLiveData() },
                              remoteResult: (suspend () -> Result<A>)? = null,
                              saveFetchResult: suspend (A) -> Unit = { Unit },
                              dispatcher: CoroutineDispatcher = Dispatchers.IO,
                              shouldFetch: () -> Boolean = { true }
): LiveData<Result<T>> =
        liveData(dispatcher) {
            emit(Result.Loading)
            val source: LiveData<Result<T>> = localResult.invoke()
                    .map { Result.Success(it) }
            emitSource(source)
            remoteResult?.let {
                if (shouldFetch.invoke()) {
                    when (val response = it.invoke()) {
                        is Result.Success -> {
                            saveFetchResult(response.data)
                        }
                        is Result.Error -> {
                            emit(Result.Error<T>(response.message))
                            emitSource((source))
                        }
                        else -> {
                        }
                    }
                }
            }
            emit(Result.Finish)
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