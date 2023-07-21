package com.theblackbit.animemania.android.di

import com.theblackbit.animemania.android.domain.usecase.CollectPagingDataUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single { CollectPagingDataUseCaseImpl() }
}
