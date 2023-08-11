package com.theblackbit.animemania.android.data.di.external

import com.theblackbit.animemania.android.data.external.repository.AnimeEpisodesByKitsuRepository
import org.koin.dsl.module

val animeEpisodesRepositoryModule = module {
    single { AnimeEpisodesByKitsuRepository(get(), get()) }
}
