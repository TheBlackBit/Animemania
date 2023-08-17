package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single

interface AnimeRemoteRepository {

    fun collectTrending(
        pageLimit: String,
        pageOffset: String?,
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>>

    fun getMostAnticipated(
        pageLimit: String,
        pageOffset: String?,
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>>

    fun getTopRated(
        pageLimit: String,
        pageOffset: String?,
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>>

    fun getPopular(
        pageLimit: String,
        pageOffset: String?,
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>>
}
