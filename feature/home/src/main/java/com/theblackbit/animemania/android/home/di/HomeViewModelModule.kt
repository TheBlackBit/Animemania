package com.theblackbit.animemania.android.home.di

import com.theblackbit.animemania.android.domain.di.ANIME_QUALIFIER
import com.theblackbit.animemania.android.domain.di.MANGA_QUALIFIER
import com.theblackbit.animemania.android.home.CollectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModel {
        CollectionViewModel(
            get(qualifier = named(ANIME_QUALIFIER)),
            get(qualifier = named(MANGA_QUALIFIER)),
            get(qualifier = named(ANIME_QUALIFIER)),
            get(qualifier = named(MANGA_QUALIFIER)),
        )
    }
}
