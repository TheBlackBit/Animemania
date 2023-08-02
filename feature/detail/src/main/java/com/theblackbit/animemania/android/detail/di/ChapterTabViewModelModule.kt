package com.theblackbit.animemania.android.detail.di

import com.theblackbit.animemania.android.detail.pagertabs.chapter.ChapterTabViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chapterTabViewModelModule = module {
    viewModel {
        ChapterTabViewModel(chaptersUseCase = get())
    }
}
