package com.theblackbit.animemania.android.domain.di

import com.theblackbit.animemania.android.domain.usecase.CollectChaptersUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectChaptersUseCaseImpl
import org.koin.dsl.module

val collectChaptersUseCaseModule = module {
    single<CollectChaptersUseCase> { CollectChaptersUseCaseImpl() }
}
