package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import io.reactivex.rxjava3.core.Single

interface CollectionRepository {
    fun collectTrendingCollection(
        pageLimit: String,
        pageOffset: String,
    ): Single<CollectionResponse>

    fun getMostWantedCollection(
        pageLimit: String,
        pageOffset: String,
    ): Single<CollectionResponse>

    fun getTopRatedCollection(
        pageLimit: String,
        pageOffset: String,
    ): Single<CollectionResponse>

    fun getPopularCollection(
        pageLimit: String,
        pageOffset: String,
    ): Single<CollectionResponse>
}
