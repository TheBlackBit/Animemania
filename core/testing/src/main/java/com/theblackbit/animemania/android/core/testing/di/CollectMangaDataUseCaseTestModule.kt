package com.theblackbit.animemania.android.core.testing.di

import com.theblackbit.animemania.android.core.testing.domain.usecase.CollectMangaDataUseCaseTest
import com.theblackbit.animemania.android.domain.di.MANGA_COLLECTION_QUALIFIER
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val collectMangaDataUseCaseTestModule = module {
    factory<CollectCollectionDataUseCase>(qualifier = named(MANGA_COLLECTION_QUALIFIER)) { CollectMangaDataUseCaseTest() } bind CollectCollectionDataUseCase::class
}
