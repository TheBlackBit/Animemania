package com.theblackbit.animemania.android.domain.di

import androidx.lifecycle.ViewModel
import com.theblackbit.animemania.android.domain.usecase.CollectChaptersUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectChaptersUseCaseImpl
import org.koin.dsl.module

val collectChaptersUseCaseModule = module {
    scope<ViewModel> {
        scoped<CollectChaptersUseCase> { CollectChaptersUseCaseImpl() }
    }
}
