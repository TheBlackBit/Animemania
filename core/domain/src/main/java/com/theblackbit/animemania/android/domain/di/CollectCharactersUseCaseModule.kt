package com.theblackbit.animemania.android.domain.di

import com.theblackbit.animemania.android.domain.usecase.CollectCharactersUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCharactersUseCaseImpl
import org.koin.dsl.module

val collectCharactersUseCaseModule = module {
    single<CollectCharactersUseCase> { CollectCharactersUseCaseImpl() }
}
