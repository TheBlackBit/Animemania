package com.theblackbit.animemania.android.di

import com.theblackbit.animemania.android.home.HomeTabContentFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeTabContentFragmentViewModel(get()) }
}
