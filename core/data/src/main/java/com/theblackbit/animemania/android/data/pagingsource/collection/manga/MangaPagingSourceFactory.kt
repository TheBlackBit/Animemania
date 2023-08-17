package com.theblackbit.animemania.android.data.pagingsource.collection.manga

import androidx.paging.PagingSource
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.external.repository.MangaRemoteRepository
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.collection.EmptyCollectionPagingSource
import com.theblackbit.animemania.android.model.Collection

class MangaPagingSourceFactory(
    private val localRepository: CollectionLocalRepository,
    private val mangaRemoteRepository: MangaRemoteRepository,
) {

    fun getMangaPagingSource(requestType: RequestType): PagingSource<Int, Collection> {
        return when (requestType) {
            RequestType.TRENDING_MANGA -> TrendingMangaPagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    mangaRemoteRepository.collectTrending(pageLimit, pageOffset)
                },
                requestType = requestType,
            )

            RequestType.MOST_ANTICIPATED_MANGA -> MostAnticipatedMangaPagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    mangaRemoteRepository.getMostAnticipated(pageLimit, pageOffset)
                },
                requestType = requestType,
            )

            RequestType.TOP_RATED_MANGA -> TopRatedMangaPagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    mangaRemoteRepository.getTopRated(pageLimit, pageOffset)
                },
                requestType = requestType,
            )

            RequestType.POPULAR_MANGA -> PopularMangaPagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    mangaRemoteRepository.getPopular(pageLimit, pageOffset)
                },
                requestType = requestType,
            )

            else -> EmptyCollectionPagingSource()
        }
    }
}
