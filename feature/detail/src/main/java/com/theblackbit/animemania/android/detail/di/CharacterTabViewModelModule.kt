package com.theblackbit.animemania.android.detail.di

import com.theblackbit.animemania.android.detail.pagertabs.character.CharactersTabViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterTabViewModelModule = module {
    viewModel {
        CharactersTabViewModel(charactersUseCase = get())
    }
}
