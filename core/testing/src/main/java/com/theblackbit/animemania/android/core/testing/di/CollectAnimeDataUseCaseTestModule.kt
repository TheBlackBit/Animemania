package com.theblackbit.animemania.android.core.testing.di

import com.theblackbit.animemania.android.core.testing.domain.usecase.CollectAnimeDataUseCaseTest
import com.theblackbit.animemania.android.domain.di.ANIME_COLLECTION_QUALIFIER
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val collectAnimeDataUseCaseTestModule = module {
    factory<CollectCollectionDataUseCase>(qualifier = named(ANIME_COLLECTION_QUALIFIER)) { CollectAnimeDataUseCaseTest() } bind CollectCollectionDataUseCase::class
}
