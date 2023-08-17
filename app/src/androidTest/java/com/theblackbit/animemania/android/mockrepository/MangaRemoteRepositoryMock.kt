package com.theblackbit.animemania.android.mockrepository

import android.content.Context
import com.theblackbit.animemania.android.core.testing.helpers.jsonFileToKotlinClass
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.data.external.repository.MangaRemoteRepository
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single

class MangaRemoteRepositoryMock(private val context: Context) : MangaRemoteRepository {
    override fun collectTrending(
        pageLimit: String,
        pageOffset: String?
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        return Single.just(
            SafeApiRequest.ApiResultHandle.Success(
                jsonFileToKotlinClass(context, "trending_manga_response_test.json")
            )
        )
    }

    override fun getMostAnticipated(
        pageLimit: String,
        pageOffset: String?
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        return Single.just(
            SafeApiRequest.ApiResultHandle.Success(
                jsonFileToKotlinClass(
                    context,
                    "most_anticipated_manga_response_first_page_test.json"
                )
            )
        )
    }

    override fun getTopRated(
        pageLimit: String,
        pageOffset: String?
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        return Single.just(
            SafeApiRequest.ApiResultHandle.Success(
                jsonFileToKotlinClass(
                    context,
                    "top_rated_manga_response_first_page_test.json"
                )
            )
        )
    }

    override fun getPopular(
        pageLimit: String,
        pageOffset: String?
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        return Single.just(
            SafeApiRequest.ApiResultHandle.Success(
                jsonFileToKotlinClass(
                    context,
                    "popular_manga_response_first_page_test.json"
                )
            )
        )
    }
}
