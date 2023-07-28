package com.theblackbit.animemania.android.domain.di

import com.theblackbit.animemania.android.domain.usecase.CollectAnimeCategoriesUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ANIME_CATEGORY_QUALIFIER = "ANIME_CATEGORY"

val collectAnimeCategoriesUseCaseModule = module {
    single<CollectCategoriesUseCase>(qualifier = named(ANIME_CATEGORY_QUALIFIER)) { CollectAnimeCategoriesUseCase() }
}
