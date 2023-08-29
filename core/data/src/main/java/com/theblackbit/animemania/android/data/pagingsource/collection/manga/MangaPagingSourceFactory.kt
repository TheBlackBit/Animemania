package com.theblackbit.animemania.android.data.pagingsource.collection.manga

import androidx.paging.rxjava3.RxPagingSource
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.external.repository.MangaRemoteRepository
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.collection.EmptyCollectionPagingSource
import com.theblackbit.animemania.android.model.Collection

class MangaPagingSourceFactory(
    private val localRepository: CollectionLocalRepository,
    private val mangaRemoteRepository: MangaRemoteRepository
) {

    fun getMangaPagingSource(requestType: RequestType): RxPagingSource<Int, Collection> {
        return when (requestType) {
            RequestType.TRENDING_MANGA -> trendingMangaPagingSource()
            RequestType.MOST_ANTICIPATED_MANGA -> mostAnticipatedMangaPagingSource()
            RequestType.TOP_RATED_MANGA -> topRatedMangaPagingSource()
            RequestType.POPULAR_MANGA -> popularMangaPagingSource()
            else -> EmptyCollectionPagingSource()
        }
    }

    private fun trendingMangaPagingSource(): RxPagingSource<Int, Collection> {
        return TrendingMangaPagingSource(
            localRepository = localRepository,
            request = { pageLimit, pageOffset ->
                mangaRemoteRepository.collectTrending(pageLimit, pageOffset)
            },
            requestType = RequestType.TRENDING_MANGA
        )
    }

    private fun mostAnticipatedMangaPagingSource(): RxPagingSource<Int, Collection> {
        return MostAnticipatedMangaPagingSource(
            localRepository = localRepository,
            request = { pageLimit, pageOffset ->
                mangaRemoteRepository.getMostAnticipated(pageLimit, pageOffset)
            },
            requestType = RequestType.MOST_ANTICIPATED_MANGA
        )
    }

    private fun topRatedMangaPagingSource(): RxPagingSource<Int, Collection> {
        return TopRatedMangaPagingSource(
            localRepository = localRepository,
            request = { pageLimit, pageOffset ->
                mangaRemoteRepository.getTopRated(pageLimit, pageOffset)
            },
            requestType = RequestType.TOP_RATED_MANGA
        )
    }

    private fun popularMangaPagingSource(): RxPagingSource<Int, Collection> {
        return PopularMangaPagingSource(
            localRepository = localRepository,
            request = { pageLimit, pageOffset ->
                mangaRemoteRepository.getPopular(pageLimit, pageOffset)
            },
            requestType = RequestType.POPULAR_MANGA
        )
    }
}
