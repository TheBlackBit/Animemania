package com.theblackbit.animemania.android.data.di.external

import com.theblackbit.animemania.android.data.external.repository.AnimeCategoriesRepository
import org.koin.dsl.module

val animeCategoriesRepositoryModule = module {
    single { AnimeCategoriesRepository() }
}
