package com.theblackbit.animemania.android.di

import com.theblackbit.animemania.android.home.CollectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CollectionViewModel(get(qualifier = named(ANIME_QUALIFIER)), get(qualifier = named(MANGA_QUALIFIER))) }
}
