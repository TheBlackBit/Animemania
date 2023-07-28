package com.theblackbit.animemania.android.home.di

import com.theblackbit.animemania.android.domain.di.MANGA_CATEGORY_QUALIFIER
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import com.theblackbit.animemania.android.home.domain.usecase.CollectMangaCategoriesUseCaseTest
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val collectMangaCategoriesUseCaseModuleTest = module {
    factory<CollectCategoriesUseCase>(qualifier = named(MANGA_CATEGORY_QUALIFIER)) { CollectMangaCategoriesUseCaseTest() } bind CollectCategoriesUseCase::class
}
