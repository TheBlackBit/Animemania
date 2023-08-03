package com.theblackbit.animemania.android.domain.di

import androidx.lifecycle.ViewModel
import com.theblackbit.animemania.android.domain.usecase.CollectAnimeDataUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ANIME_COLLECTION_QUALIFIER = "ANIME"

val collectAnimeUseCaseModule = module {
    scope<ViewModel> {
        scoped<CollectCollectionDataUseCase>(qualifier = named(ANIME_COLLECTION_QUALIFIER)) { CollectAnimeDataUseCase() }
    }
}
