package com.theblackbit.animemania.android.home.mockrepository

import android.content.Context
import com.theblackbit.animemania.android.core.testing.helpers.jsonFileToKotlinClass
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.data.external.repository.MangaRemoteRepository
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class MangaRemoteRepositoryMock(private val context: Context) : MangaRemoteRepository {
    override fun collectTrending(
        pageLimit: String,
        pageOffset: String?
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        val delay = if (pageOffset == null) 1L else 0
        return Single.just<SafeApiRequest.ApiResultHandle<CollectionResponse>>(
            SafeApiRequest.ApiResultHandle.Success(
                jsonFileToKotlinClass(context, "trending_manga_response_test.json")
            )
        ).delay(delay, TimeUnit.SECONDS)
    }

    override fun getMostAnticipated(
        pageLimit: String,
        pageOffset: String?
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        val delay = if (pageOffset == null) 1L else 0
        val response = when (pageOffset) {
            null -> jsonFileToKotlinClass(
                context,
                "most_anticipated_manga_response_first_page_test.json"
            )

            "20" -> jsonFileToKotlinClass(
                context,
                "most_anticipated_manga_response_second_page_test.json"
            )

            else -> CollectionResponse(emptyList())
        }
        return Single.just<SafeApiRequest.ApiResultHandle<CollectionResponse>>(
            SafeApiRequest.ApiResultHandle.Success(
                response
            )
        ).delay(delay, TimeUnit.SECONDS)
    }

    override fun getTopRated(
        pageLimit: String,
        pageOffset: String?
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        val delay = if (pageOffset == null) 1L else 0
        val response = when (pageOffset) {
            null -> jsonFileToKotlinClass(
                context,
                "top_rated_manga_response_first_page_test.json"
            )

            "20" -> jsonFileToKotlinClass(
                context,
                "top_rated_manga_response_second_page_test.json"
            )

            else -> CollectionResponse(emptyList())
        }
        return Single.just<SafeApiRequest.ApiResultHandle<CollectionResponse>>(
            SafeApiRequest.ApiResultHandle.Success(
                response
            )
        ).delay(delay, TimeUnit.SECONDS)
    }

    override fun getPopular(
        pageLimit: String,
        pageOffset: String?
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        val delay = if (pageOffset == null) 1L else 0
        val response = when (pageOffset) {
            null -> jsonFileToKotlinClass(
                context,
                "popular_manga_response_first_page_test.json"
            )

            "20" -> jsonFileToKotlinClass(
                context,
                "popular_manga_response_second_page_test.json"
            )

            else -> CollectionResponse(emptyList())
        }
        return Single.just<SafeApiRequest.ApiResultHandle<CollectionResponse>>(
            SafeApiRequest.ApiResultHandle.Success(
                response
            )
        ).delay(delay, TimeUnit.SECONDS)
    }
}
