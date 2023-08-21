package com.theblackbit.animemania.android.data.di.pagingsource

import com.theblackbit.animemania.android.data.pagingsource.collection.anime.AnimePagingSourceFactory
import org.koin.dsl.module

val animePagingSourceFactoryModule = module {
    single { AnimePagingSourceFactory(get(), get()) }
}
