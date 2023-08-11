package com.theblackbit.animemania.android.data.di.external

import com.theblackbit.animemania.android.data.external.repository.CharactersByKitsuRepositoryImpl
import org.koin.dsl.module

val charactersRepositoryModule = module {
    single { CharactersByKitsuRepositoryImpl(get(), get()) }
}
