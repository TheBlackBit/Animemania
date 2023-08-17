package com.theblackbit.animemania.android.data.di.pagingsource

import com.theblackbit.animemania.android.data.pagingsource.collection.manga.MangaPagingSourceFactory
import org.koin.dsl.module

val mangaPagingSourceFactoryModule = module {
    single { MangaPagingSourceFactory(get(), get()) }
}
