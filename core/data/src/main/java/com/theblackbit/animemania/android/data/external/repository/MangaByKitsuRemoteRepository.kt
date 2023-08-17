package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuMangaDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single

class MangaByKitsuRemoteRepository(
    private val kitsuCollectionDataSource: KitsuMangaDataSource,
    private val safeApiRequest: SafeApiRequest,
) : MangaRemoteRepository {
    override fun collectTrending(
        pageLimit: String,
        pageOffset: String?,
    ): Single<SafeApiRequest.ApiResultHandle<CollectionResponse>> {
        return safeApiRequest.request {
            kitsuCollectionDataSource.getTrendingCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ).map { it }
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
            ).map { it }
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
            ).map { it }
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
            ).map { it }
        }
    }
}
