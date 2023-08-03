package com.theblackbit.animemania.android.data.di

import com.theblackbit.animemania.android.data.external.repository.MangaChaptersByKitsuRepositoryImpl
import org.koin.dsl.module

val mangaChaptersRepositoryModule = module {
    single { MangaChaptersByKitsuRepositoryImpl(get()) }
}
