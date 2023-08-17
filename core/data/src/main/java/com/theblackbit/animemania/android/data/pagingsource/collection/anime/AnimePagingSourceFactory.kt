package com.theblackbit.animemania.android.data.pagingsource.collection.anime

import androidx.paging.rxjava3.RxPagingSource
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.external.repository.AnimeRemoteRepository
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.collection.EmptyCollectionPagingSource
import com.theblackbit.animemania.android.model.Collection

class AnimePagingSourceFactory(
    private val localRepository: CollectionLocalRepository,
    private val animeRemoteRepository: AnimeRemoteRepository,
) {

    fun getAnimePagingSource(requestType: RequestType): RxPagingSource<Int, Collection> {
        return when (requestType) {
            RequestType.TRENDING_ANIME -> TrendingAnimePagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    animeRemoteRepository.collectTrending(pageLimit, pageOffset)
                },
                requestType = requestType,
            )

            RequestType.MOST_ANTICIPATED_ANIME -> MostAnticipatedAnimePagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    animeRemoteRepository.getMostAnticipated(pageLimit, pageOffset)
                },
                requestType = requestType,
            )

            RequestType.TOP_RATED_ANIME -> TopRatedAnimePagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    animeRemoteRepository.getTopRated(pageLimit, pageOffset)
                },
                requestType = requestType,
            )

            RequestType.POPULAR_ANIME -> PopularAnimePagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    animeRemoteRepository.getPopular(pageLimit, pageOffset)
                },
                requestType = requestType,
            )

            else -> EmptyCollectionPagingSource()
        }
    }
}
