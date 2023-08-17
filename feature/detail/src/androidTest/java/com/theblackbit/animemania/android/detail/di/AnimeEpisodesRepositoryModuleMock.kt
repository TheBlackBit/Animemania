package com.theblackbit.animemania.android.detail.di

import com.theblackbit.animemania.android.data.external.repository.EpisodesByKitsuRepository
import com.theblackbit.animemania.android.detail.mockrepository.AnimeEpisodesByKitsuRepositoryMock
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val animeEpisodesRepositoryModuleMock = module {
    single<EpisodesByKitsuRepository> { AnimeEpisodesByKitsuRepositoryMock(androidContext()) }
}
