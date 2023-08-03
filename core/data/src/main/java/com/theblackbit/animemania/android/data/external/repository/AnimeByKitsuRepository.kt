package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuCollectionDataSource
import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuMediaType.ANIME_MEDIA_TYPE
import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuMediaType.ANIME_TRENDING
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import io.reactivex.rxjava3.core.Single

class AnimeByKitsuRepository(
    private val kitsuCollectionDataSource: KitsuCollectionDataSource,
) : CollectionRepository {
    override fun collectTrendingCollection(
        pageLimit: String,
        pageOffset: String,
    ): Single<CollectionResponse> {
        return kitsuCollectionDataSource.getTrendingCollection(
            path = ANIME_TRENDING,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )
    }

    override fun getMostWantedCollection(
        pageLimit: String,
        pageOffset: String,
    ): Single<CollectionResponse> {
        return kitsuCollectionDataSource.getMostWantedCollection(
            path = ANIME_MEDIA_TYPE,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )
    }

    override fun getTopRatedCollection(
        pageLimit: String,
        pageOffset: String,
    ): Single<CollectionResponse> {
        return kitsuCollectionDataSource.getTopRatedCollection(
            path = ANIME_MEDIA_TYPE,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )
    }

    override fun getPopularCollection(
        pageLimit: String,
        pageOffset: String,
    ): Single<CollectionResponse> {
        return kitsuCollectionDataSource.getPopularCollection(
            path = ANIME_MEDIA_TYPE,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )
    }
}
