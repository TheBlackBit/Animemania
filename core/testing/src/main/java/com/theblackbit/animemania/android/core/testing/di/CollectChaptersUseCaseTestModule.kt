package com.theblackbit.animemania.android.core.testing.di

import com.theblackbit.animemania.android.core.testing.domain.usecase.CollectChaptersUseCaseTest
import com.theblackbit.animemania.android.domain.usecase.CollectChaptersUseCase
import org.koin.dsl.module

val collectChaptersUseCaseTestModule = module {
    single<CollectChaptersUseCase> { CollectChaptersUseCaseTest() }
}
