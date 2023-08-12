package com.theblackbit.animemania.android.data.di.external

import com.theblackbit.animemania.android.data.external.repository.AnimeCategoriesRepository
import com.theblackbit.animemania.android.data.external.repository.CategoriesRemoteRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ANIME_CATEGORY_DATA_QUALIFIER = "ANIME_CATEGORY_DATA"

val animeCategoriesRepositoryModule = module {
    single<CategoriesRemoteRepository>(qualifier = named(ANIME_CATEGORY_DATA_QUALIFIER)) { AnimeCategoriesRepository() }
}
