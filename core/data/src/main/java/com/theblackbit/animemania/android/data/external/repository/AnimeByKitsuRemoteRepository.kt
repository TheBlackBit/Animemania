package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuAnimeDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single

class AnimeByKitsuRemoteRepository(
    private val kitsuCollectionDataSource: KitsuAnimeDataSource,
    private val safeApiRequest: SafeApiRequest,
) : AnimeRemoteRepository {

    override fun collectTrending(
        pageLimit: String,
        pageOffset: String?,
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        return safeApiRequest.request {
            kitsuCollectionDataSource.getTrendingCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            )
        }
    }

    override fun getMostAnticipated(
        pageLimit: String,
        pageOffset: String?,
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        return safeApiRequest.request {
            kitsuCollectionDataSource.getMostWantedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            )
        }
    }

    override fun getTopRated(
        pageLimit: String,
        pageOffset: String?,
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        return safeApiRequest.request {
            kitsuCollectionDataSource.getTopRatedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            )
        }
    }

    override fun getPopular(
        pageLimit: String,
        pageOffset: String?,
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        return safeApiRequest.request {
            kitsuCollectionDataSource.getPopularCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            )
        }
    }
}
