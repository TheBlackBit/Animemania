package com.theblackbit.animemania.android.domain.di

import com.theblackbit.animemania.android.data.di.external.ANIME_CATEGORY_DATA_QUALIFIER
import com.theblackbit.animemania.android.domain.usecase.CollectAnimeCategoriesUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ANIME_CATEGORY_QUALIFIER = "ANIME_CATEGORY"

val collectAnimeCategoriesUseCaseModule = module {
    factory<CollectCategoriesUseCase>(qualifier = named(ANIME_CATEGORY_QUALIFIER)) {
        CollectAnimeCategoriesUseCase(
            get(qualifier = named(ANIME_CATEGORY_DATA_QUALIFIER)),
        )
    }
}
