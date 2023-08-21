package com.theblackbit.animemania.android.home.di

import com.theblackbit.animemania.android.domain.di.ANIME_COLLECTION_QUALIFIER
import com.theblackbit.animemania.android.domain.di.MANGA_COLLECTION_QUALIFIER
import com.theblackbit.animemania.android.home.CollectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModel {
        CollectionViewModel(
            collectAnimeDataUseCase = get(qualifier = named(ANIME_COLLECTION_QUALIFIER)),
            collectMangaDataUseCase = get(qualifier = named(MANGA_COLLECTION_QUALIFIER))
        )
    }
}
