package com.theblackbit.animemania.android.domain.di

import androidx.lifecycle.ViewModel
import com.theblackbit.animemania.android.domain.usecase.CollectCharactersUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCharactersUseCaseImpl
import org.koin.dsl.module

val collectCharactersUseCaseModule = module {
    scope<ViewModel> {
        scoped<CollectCharactersUseCase> { CollectCharactersUseCaseImpl() }
    }
}
