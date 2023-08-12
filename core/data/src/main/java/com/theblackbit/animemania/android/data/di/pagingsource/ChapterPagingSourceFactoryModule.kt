package com.theblackbit.animemania.android.data.di.pagingsource

import com.theblackbit.animemania.android.data.pagingsource.chapter.ChapterPagingSourceFactory
import org.koin.dsl.module

val chapterPagingSourceFactoryModule = module {
    single { ChapterPagingSourceFactory(get(), get(), get()) }
}
