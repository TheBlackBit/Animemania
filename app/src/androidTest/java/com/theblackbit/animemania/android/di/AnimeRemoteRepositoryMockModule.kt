package com.theblackbit.animemania.android.di

import com.theblackbit.animemania.android.data.external.repository.AnimeRemoteRepository
import com.theblackbit.animemania.android.mockrepository.AnimeRemoteRepositoryMock
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val animeRemoteRepositoryMock = module {
    single<AnimeRemoteRepository> { AnimeRemoteRepositoryMock(androidContext()) }
}
