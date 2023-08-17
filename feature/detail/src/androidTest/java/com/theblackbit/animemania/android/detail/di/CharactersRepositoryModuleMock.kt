package com.theblackbit.animemania.android.detail.di

import com.theblackbit.animemania.android.data.external.repository.CharacterByKitsuRepository
import com.theblackbit.animemania.android.detail.mockrepository.CharacterByKitsuRepositoryMock
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val charactersRepositoryModuleMock = module {
    single<CharacterByKitsuRepository> { CharacterByKitsuRepositoryMock(androidContext()) }
}
