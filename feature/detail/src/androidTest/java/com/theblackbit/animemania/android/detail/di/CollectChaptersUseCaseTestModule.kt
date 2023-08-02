package com.theblackbit.animemania.android.detail.di

import com.theblackbit.animemania.android.detail.domain.usecase.CollectChaptersUseCaseTest
import com.theblackbit.animemania.android.domain.usecase.CollectChaptersUseCase
import org.koin.dsl.module

val collectChaptersUseCaseTestModule = module {
    single<CollectChaptersUseCase> { CollectChaptersUseCaseTest() }
}
