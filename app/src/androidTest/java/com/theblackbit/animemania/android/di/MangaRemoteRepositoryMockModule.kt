package com.theblackbit.animemania.android.di

import com.theblackbit.animemania.android.data.external.repository.MangaRemoteRepository
import com.theblackbit.animemania.android.mockrepository.MangaRemoteRepositoryMock
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mangaRemoteRepositoryMock = module {
    single<MangaRemoteRepository> { MangaRemoteRepositoryMock(androidContext()) }
}
