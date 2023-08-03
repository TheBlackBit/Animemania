package com.theblackbit.animemania.android.domain.di

import androidx.lifecycle.ViewModel
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectMangaDataUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MANGA_COLLECTION_QUALIFIER = "MANGA"

val collectMangaUseCaseModule = module {
    scope<ViewModel> {
        scoped<CollectCollectionDataUseCase>(qualifier = named(MANGA_COLLECTION_QUALIFIER)) { CollectMangaDataUseCase() }
    }
}
