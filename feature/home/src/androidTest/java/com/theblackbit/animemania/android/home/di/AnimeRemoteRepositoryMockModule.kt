package com.theblackbit.animemania.android.home.di

import com.theblackbit.animemania.android.data.external.repository.AnimeRemoteRepository
import com.theblackbit.animemania.android.home.mockrepository.AnimeRemoteRepositoryMock
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val animeRemoteRepositoryMock = module {
    single<AnimeRemoteRepository> { AnimeRemoteRepositoryMock(androidContext()) }
}
