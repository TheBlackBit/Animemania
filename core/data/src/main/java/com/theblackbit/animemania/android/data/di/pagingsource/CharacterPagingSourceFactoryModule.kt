package com.theblackbit.animemania.android.data.di.pagingsource

import com.theblackbit.animemania.android.data.pagingsource.character.CharacterPagingSourceFactory
import org.koin.dsl.module

val characterFactoryPagingSourceModule = module {
    single { CharacterPagingSourceFactory(get(), get()) }
}
