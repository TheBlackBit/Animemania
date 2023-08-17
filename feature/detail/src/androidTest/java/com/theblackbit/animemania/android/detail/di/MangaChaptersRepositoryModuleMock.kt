package com.theblackbit.animemania.android.detail.di

import com.theblackbit.animemania.android.data.external.repository.MangaChaptersByKitsuRepository
import com.theblackbit.animemania.android.detail.mockrepository.MangaChaptersByKitsuRepositoryMock
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val mangaChaptersRepositoryModuleMock = module {
    single<MangaChaptersByKitsuRepository> { MangaChaptersByKitsuRepositoryMock(androidContext()) }
}
