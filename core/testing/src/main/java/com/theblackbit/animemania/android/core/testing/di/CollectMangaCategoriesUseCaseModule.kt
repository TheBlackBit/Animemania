package com.theblackbit.animemania.android.core.testing.di

import com.theblackbit.animemania.android.core.testing.domain.usecase.CollectMangaCategoriesUseCaseTest
import com.theblackbit.animemania.android.domain.di.MANGA_CATEGORY_QUALIFIER
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val collectMangaCategoriesUseCaseModuleTest = module {
    factory<CollectCategoriesUseCase>(qualifier = named(MANGA_CATEGORY_QUALIFIER)) { CollectMangaCategoriesUseCaseTest() } bind CollectCategoriesUseCase::class
}
