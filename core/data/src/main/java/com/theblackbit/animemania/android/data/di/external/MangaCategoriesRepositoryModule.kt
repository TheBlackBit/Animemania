package com.theblackbit.animemania.android.data.di.external

import com.theblackbit.animemania.android.data.external.repository.MangaCategoriesRepository
import org.koin.dsl.module

val mangaCategoriesRepositoryModule = module {
    single { MangaCategoriesRepository() }
}
