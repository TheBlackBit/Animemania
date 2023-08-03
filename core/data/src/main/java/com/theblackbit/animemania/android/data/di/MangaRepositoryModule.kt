package com.theblackbit.animemania.android.data.di

import com.theblackbit.animemania.android.data.external.repository.CollectionRepository
import com.theblackbit.animemania.android.data.external.repository.MangaByKitsuRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MANGA_COLLECTION_QUALIFIER = "MANGA"

val mangaRepositoryModule = module {
    single<CollectionRepository>(qualifier = named(MANGA_COLLECTION_QUALIFIER)) { MangaByKitsuRepository(get()) }
}
