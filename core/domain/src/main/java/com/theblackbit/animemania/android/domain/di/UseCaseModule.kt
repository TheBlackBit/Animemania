package com.theblackbit.animemania.android.domain.di

import com.theblackbit.animemania.android.domain.usecase.CollectAnimeCategoriesUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectAnimeDataUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectMangaCategoriesUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectMangaDataUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

const val ANIME_QUALIFIER = "ANIME"
const val MANGA_QUALIFIER = "MAGA"

val useCaseModule = module {
    single<CollectCollectionDataUseCase>(qualifier = named(ANIME_QUALIFIER)) { CollectAnimeDataUseCase() } bind CollectCollectionDataUseCase::class
    single<CollectCollectionDataUseCase>(qualifier = named(MANGA_QUALIFIER)) { CollectMangaDataUseCase() } bind CollectCollectionDataUseCase::class
    single<CollectCategoriesUseCase>(qualifier = named(ANIME_QUALIFIER)) { CollectAnimeCategoriesUseCase() } bind CollectCategoriesUseCase::class
    single<CollectCategoriesUseCase>(qualifier = named(MANGA_QUALIFIER)) { CollectMangaCategoriesUseCase() } bind CollectCategoriesUseCase::class
}
