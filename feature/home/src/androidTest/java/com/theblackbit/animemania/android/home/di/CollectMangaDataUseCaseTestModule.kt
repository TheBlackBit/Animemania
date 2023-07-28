package com.theblackbit.animemania.android.home.di

import com.theblackbit.animemania.android.domain.di.MANGA_COLLECTION_QUALIFIER
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.home.domain.usecase.CollectMangaDataUseCaseTest
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val collectMangaDataUseCaseTestModule = module {
    factory<CollectCollectionDataUseCase>(qualifier = named(MANGA_COLLECTION_QUALIFIER)) { CollectMangaDataUseCaseTest() } bind CollectCollectionDataUseCase::class
}
