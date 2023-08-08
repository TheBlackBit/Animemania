package com.theblackbit.animemania.android.data.di

import com.theblackbit.animemania.android.data.external.repository.AnimeByKitsuRepository
import com.theblackbit.animemania.android.data.external.repository.CollectionRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ANIME_COLLECTION_QUALIFIER = "ANIME"

val animeRepositoryModule = module {
    single<CollectionRepository>(qualifier = named(ANIME_COLLECTION_QUALIFIER)) {
        AnimeByKitsuRepository(
            get(),
        )
    }
}
