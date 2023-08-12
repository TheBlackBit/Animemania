package com.theblackbit.animemania.android.data.di.pagingsource

import com.theblackbit.animemania.android.data.pagingsource.collection.manga.MangaPagingSourceFactory
import org.koin.dsl.module

val mangaPagingSourceFactory = module {
    single { MangaPagingSourceFactory(get(), get()) }
}
