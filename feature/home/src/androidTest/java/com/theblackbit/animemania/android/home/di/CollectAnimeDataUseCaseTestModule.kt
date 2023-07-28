package com.theblackbit.animemania.android.home.di

import com.theblackbit.animemania.android.domain.di.ANIME_COLLECTION_QUALIFIER
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.home.domain.usecase.CollectAnimeDataUseCaseTest
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val collectAnimeDataUseCaseTestModule = module {
    factory<CollectCollectionDataUseCase>(qualifier = named(ANIME_COLLECTION_QUALIFIER)) { CollectAnimeDataUseCaseTest() } bind CollectCollectionDataUseCase::class
}
