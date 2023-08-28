package com.theblackbit.animemania.android.domain.di

import androidx.paging.PagingConfig
import com.theblackbit.animemania.android.data.pagingsource.collection.CollectionPagingSource
import com.theblackbit.animemania.android.domain.usecase.CollectAnimeDataUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ANIME_COLLECTION_QUALIFIER = "ANIME_COLLECTION"

val collectAnimeUseCaseModule = module {
    factory<CollectCollectionDataUseCase>(qualifier = named(ANIME_COLLECTION_QUALIFIER)) {
        CollectAnimeDataUseCase(
            get(),
            PagingConfig(
                pageSize = CollectionPagingSource.COLLECTION_PAGE_LIMIT,
                prefetchDistance = CollectionPagingSource.COLLECTION_PAGE_LIMIT / 2
            ),
        )
    }
}
