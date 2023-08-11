package com.theblackbit.animemania.android.data.pagingsource.collection.manga

import androidx.paging.PagingSource
import com.theblackbit.animemania.android.data.external.datasource.categories.MOST_ANTICIPATED_MANGA_ID
import com.theblackbit.animemania.android.data.external.datasource.categories.POPULAR_MANGA_ID
import com.theblackbit.animemania.android.data.external.datasource.categories.TOP_RATED_MANGA_ID
import com.theblackbit.animemania.android.data.external.datasource.categories.TRENDING_MANGA_ID
import com.theblackbit.animemania.android.data.external.repository.MangaRemoteRepository
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.collection.EmptyCollectionPagingSource
import com.theblackbit.animemania.android.model.Collection

class MangaPagingSourceFactory(
    private val localRepository: CollectionLocalRepository,
    private val mangaRemoteRepository: MangaRemoteRepository,
) {

    fun getMangaPagingSource(categoryId: Int): PagingSource<Int, Collection> {
        return when (categoryId) {
            TRENDING_MANGA_ID -> TrendingMangaPagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    mangaRemoteRepository.collectTrending(pageLimit, pageOffset)
                },
                categoryId = categoryId,
            )

            MOST_ANTICIPATED_MANGA_ID -> MostAnticipatedMangaPagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    mangaRemoteRepository.getMostAnticipated(pageLimit, pageOffset)
                },
                categoryId = categoryId,
            )

            TOP_RATED_MANGA_ID -> TopRatedMangaPagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    mangaRemoteRepository.getTopRated(pageLimit, pageOffset)
                },
                categoryId = categoryId,
            )

            POPULAR_MANGA_ID -> PopularMangaPagingSource(
                localRepository = localRepository,
                request = { pageLimit, pageOffset ->
                    mangaRemoteRepository.getPopular(pageLimit, pageOffset)
                },
                categoryId = categoryId,
            )

            else -> EmptyCollectionPagingSource()
        }
    }
}
