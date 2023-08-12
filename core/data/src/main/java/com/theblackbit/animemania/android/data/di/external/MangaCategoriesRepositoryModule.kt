package com.theblackbit.animemania.android.data.di.external

import com.theblackbit.animemania.android.data.external.repository.CategoriesRemoteRepository
import com.theblackbit.animemania.android.data.external.repository.MangaCategoriesRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MANGA_CATEGORY_QUALIFIER_DATA = "MANGA_CATEGORY_DATA"

val mangaCategoriesRepositoryModule = module {
    single<CategoriesRemoteRepository>(qualifier = named(MANGA_CATEGORY_QUALIFIER_DATA)) { MangaCategoriesRepository() }
}
