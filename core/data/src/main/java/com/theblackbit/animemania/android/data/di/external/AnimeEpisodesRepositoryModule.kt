package com.theblackbit.animemania.android.data.di.external

import com.theblackbit.animemania.android.data.external.repository.AnimeEpisodesByKitsuRepository
import com.theblackbit.animemania.android.data.external.repository.EpisodesByKitsuRepository
import org.koin.dsl.module

val animeEpisodesRepositoryModule = module {
    single<EpisodesByKitsuRepository> { AnimeEpisodesByKitsuRepository(get(), get()) }
}
