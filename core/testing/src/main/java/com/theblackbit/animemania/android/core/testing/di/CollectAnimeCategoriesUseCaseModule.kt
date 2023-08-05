package com.theblackbit.animemania.android.core.testing.di

import com.theblackbit.animemania.android.core.testing.domain.usecase.CollectAnimeCategoriesUseCaseTest
import com.theblackbit.animemania.android.domain.di.ANIME_CATEGORY_QUALIFIER
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val collectAnimeCategoriesUseCaseModuleTest = module {
    factory<CollectCategoriesUseCase>(qualifier = named(ANIME_CATEGORY_QUALIFIER)) { CollectAnimeCategoriesUseCaseTest() } bind CollectCategoriesUseCase::class
}
