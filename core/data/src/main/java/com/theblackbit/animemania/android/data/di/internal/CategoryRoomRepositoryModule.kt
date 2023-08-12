package com.theblackbit.animemania.android.data.di.internal

import com.theblackbit.animemania.android.data.internal.repository.CategoryLocalRepository
import com.theblackbit.animemania.android.data.internal.repository.CategoryRoomRepository
import org.koin.dsl.module

val categoryRoomRepositoryModule = module {
    single<CategoryLocalRepository> { CategoryRoomRepository(get()) }
}
