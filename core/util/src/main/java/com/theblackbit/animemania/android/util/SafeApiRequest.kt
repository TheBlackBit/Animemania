package com.theblackbit.animemania.android.util

import io.reactivex.rxjava3.core.Single
import retrofit2.HttpException
import java.io.IOException

class SafeApiRequest {
    inline fun <reified T> request(
        apiRequest: () -> Single<Any>
    ): Single<ApiResultHandle<T>> {
        return apiRequest.invoke()
            .flatMap { response ->
                val result = when (response) {
                    is T -> ApiResultHandle.Success(response)
                    else -> ApiResultHandle.ApiError
                } as ApiResultHandle<T>
                Single.just(result)
            }
            .onErrorReturn { throwable ->
                when (throwable) {
                    is IOException -> ApiResultHandle.NetworkError
                    is HttpException -> ApiResultHandle.ApiError
                    else -> ApiResultHandle.ApiError
                }
            }
    }

    sealed class ApiResultHandle<out T> {
        data class Success<out T>(val value: T) : ApiResultHandle<T>()
        object ApiError : ApiResultHandle<Nothing>()
        object NetworkError : ApiResultHandle<Nothing>()
    }
}
