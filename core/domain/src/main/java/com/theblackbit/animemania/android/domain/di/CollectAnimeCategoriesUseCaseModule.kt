package com.theblackbit.animemania.android.domain.di

import androidx.lifecycle.ViewModel
import com.theblackbit.animemania.android.domain.usecase.CollectAnimeCategoriesUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ANIME_CATEGORY_QUALIFIER = "ANIME_CATEGORY"

val collectAnimeCategoriesUseCaseModule = module {
    scope<ViewModel> {
        scoped<CollectCategoriesUseCase>(qualifier = named(ANIME_CATEGORY_QUALIFIER)) { CollectAnimeCategoriesUseCase() }
    }
}
