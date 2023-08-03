package com.theblackbit.animemania.android.domain.di

import androidx.lifecycle.ViewModel
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectMangaCategoriesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MANGA_CATEGORY_QUALIFIER = "MANGA_CATEGORY"
val collectMangaCategoriesUseCaseModule = module {
    scope<ViewModel> {
        scoped<CollectCategoriesUseCase>(qualifier = named(MANGA_CATEGORY_QUALIFIER)) { CollectMangaCategoriesUseCase() }
    }
}
