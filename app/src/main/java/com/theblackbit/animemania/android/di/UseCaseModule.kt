package com.theblackbit.animemania.android.di

import com.theblackbit.animemania.android.domain.usecase.CollectAnimeDataUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectMangaDataUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

const val ANIME_QUALIFIER = "ANIME"
const val MANGA_QUALIFIER = "MAGA"

val useCaseModule = module {
    single<CollectCollectionDataUseCase>(qualifier = named(ANIME_QUALIFIER)) { CollectAnimeDataUseCase() } bind CollectCollectionDataUseCase::class
    single<CollectCollectionDataUseCase>(qualifier = named(MANGA_QUALIFIER)) { CollectMangaDataUseCase() } bind CollectCollectionDataUseCase::class
}
