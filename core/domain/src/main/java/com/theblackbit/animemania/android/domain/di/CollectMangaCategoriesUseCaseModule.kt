package com.theblackbit.animemania.android.domain.di

import com.theblackbit.animemania.android.data.di.external.MANGA_CATEGORY_QUALIFIER_DATA
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectMangaCategoriesUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MANGA_CATEGORY_QUALIFIER = "MANGA_CATEGORY"
val collectMangaCategoriesUseCaseModule = module {
    factory<CollectCategoriesUseCase>(qualifier = named(MANGA_CATEGORY_QUALIFIER)) {
        CollectMangaCategoriesUseCase(get(qualifier = named(MANGA_CATEGORY_QUALIFIER_DATA)))
    }
}
