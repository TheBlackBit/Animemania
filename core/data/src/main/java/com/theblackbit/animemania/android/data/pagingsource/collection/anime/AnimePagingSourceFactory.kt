package com.theblackbit.animemania.android.data.pagingsource.collection.anime

import androidx.paging.rxjava3.RxPagingSource
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.external.repository.AnimeRemoteRepository
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.collection.EmptyCollectionPagingSource
import com.theblackbit.animemania.android.model.Collection

class AnimePagingSourceFactory(
    private val localRepository: CollectionLocalRepository,
    private val animeRemoteRepository: AnimeRemoteRepository
) {

    fun getAnimePagingSource(requestType: RequestType): RxPagingSource<Int, Collection> {
        return when (requestType) {
            RequestType.TRENDING_ANIME -> trendingAnimePagingSource()
            RequestType.MOST_ANTICIPATED_ANIME -> mostAnticipatedAnimePagingSource()
            RequestType.TOP_RATED_ANIME -> topRatedAnimePagingSource()
            RequestType.POPULAR_ANIME -> popularAnimePagingSource()
            else -> EmptyCollectionPagingSource()
        }
    }

    private fun trendingAnimePagingSource(): RxPagingSource<Int, Collection> {
        return TrendingAnimePagingSource(
            localRepository = localRepository,
            request = { pageLimit, pageOffset ->
                animeRemoteRepository.collectTrending(pageLimit, pageOffset)
            },
            requestType = RequestType.TRENDING_ANIME
        )
    }

    private fun mostAnticipatedAnimePagingSource(): RxPagingSource<Int, Collection> {
        return MostAnticipatedAnimePagingSource(
            localRepository = localRepository,
            request = { pageLimit, pageOffset ->
                animeRemoteRepository.getMostAnticipated(pageLimit, pageOffset)
            },
            requestType = RequestType.MOST_ANTICIPATED_ANIME
        )
    }

    private fun topRatedAnimePagingSource(): RxPagingSource<Int, Collection> {
        return TopRatedAnimePagingSource(
            localRepository = localRepository,
            request = { pageLimit, pageOffset ->
                animeRemoteRepository.getTopRated(pageLimit, pageOffset)
            },
            requestType = RequestType.TOP_RATED_ANIME
        )
    }

    private fun popularAnimePagingSource(): RxPagingSource<Int, Collection> {
        return PopularAnimePagingSource(
            localRepository = localRepository,
            request = { pageLimit, pageOffset ->
                animeRemoteRepository.getPopular(pageLimit, pageOffset)
            },
            requestType = RequestType.POPULAR_ANIME
        )
    }
}
