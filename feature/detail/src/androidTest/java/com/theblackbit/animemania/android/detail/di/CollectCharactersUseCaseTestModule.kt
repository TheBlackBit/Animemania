package com.theblackbit.animemania.android.detail.di

import com.theblackbit.animemania.android.detail.domain.usecase.CollectCharacterUseCaseTest
import com.theblackbit.animemania.android.domain.usecase.CollectCharactersUseCase
import org.koin.dsl.module

val collectCharactersUseCaseTestModule = module {
    single<CollectCharactersUseCase> { CollectCharacterUseCaseTest() }
}
