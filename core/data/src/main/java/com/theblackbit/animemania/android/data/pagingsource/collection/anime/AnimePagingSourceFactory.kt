package com.theblackbit.animemania.android.data.pagingsource.collection.anime

import androidx.paging.rxjava3.RxPagingSource
import com.theblackbit.animemania.android.data.external.datasource.categories.MOST_ANTICIPATED_ANIME_ID
import com.theblackbit.animemania.android.data.external.datasource.categories.POPULAR_ANIME_ID
import com.theblackbit.animemania.android.data.external.datasource.categories.TOP_RATED_ANIME_ID
import com.theblackbit.animemania.android.data.external.datasource.categories.TRENDING_ANIME_ID
import com.theblackbit.animemania.android.data.external.repository.AnimeRemoteRepository
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.collection.EmptyCollectionPagingSource
import com.theblackbit.animemania.android.model.Collection

class AnimePagingSourceFactory(
    private val localRepository: CollectionLocalRepository,
    private val animeRemoteRepository: AnimeRemoteRepository,
) {

    companion object {
        const val PAGE_LIMIT = 20
    }

    fun getAnimePagingSource(categoryId: Int): RxPagingSource<Int, Collection> {
        return when (categoryId) {
            TRENDING_ANIME_ID -> TrendingAnimePagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    animeRemoteRepository.collectTrending(pageLimit, pageOffset)
                },
                categoryId = categoryId,
            )

            MOST_ANTICIPATED_ANIME_ID -> MostAnticipatedAnimePagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    animeRemoteRepository.getMostAnticipated(pageLimit, pageOffset)
                },
                categoryId = categoryId,
            )

            TOP_RATED_ANIME_ID -> TopRatedAnimePagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    animeRemoteRepository.getTopRated(pageLimit, pageOffset)
                },
                categoryId = categoryId,
            )

            POPULAR_ANIME_ID -> PopularAnimePagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    animeRemoteRepository.getPopular(pageLimit, pageOffset)
                },
                categoryId = categoryId,
            )

            else -> EmptyCollectionPagingSource()
        }
    }
}
