package com.theblackbit.animemania.android.util

import io.reactivex.rxjava3.core.Single
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

class SafeApiRequestTest {

    private val safeApiRequest = SafeApiRequest()

    @Test
    fun `test success response`() {
        val responseData = "SuccessData"
        val single: Single<Any> = Single.just(responseData)

        safeApiRequest.request<String> {
            single
        }.test()
            .assertValue {
                it is SafeApiRequest.ApiResultHandle.Success
            }
    }

    @Test
    fun `test apiError response`() {
        val error =
            HttpException(retrofit2.Response.error<Any>(400, okhttp3.ResponseBody.create(null, "")))
        val single: Single<Any> = Single.error(error)

        safeApiRequest.request<String> {
            single
        }.test()
            .assertValue {
                it is SafeApiRequest.ApiResultHandle.ApiError
            }
    }

    @Test
    fun `test networkError response`() {
        val single: Single<Any> = Single.error(IOException())

        safeApiRequest.request<String> {
            single
        }.test()
            .assertValue {
                it is SafeApiRequest.ApiResultHandle.NetworkError
            }
    }

    @Test
    fun `test unknownError response`() {
        val single: Single<Any> = Single.error(Exception())

        safeApiRequest.request<String> {
            single
        }.test()
            .assertValue {
                it is SafeApiRequest.ApiResultHandle.ApiError
            }
    }
}
