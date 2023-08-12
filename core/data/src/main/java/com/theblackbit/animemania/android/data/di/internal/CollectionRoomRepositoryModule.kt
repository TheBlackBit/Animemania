package com.theblackbit.animemania.android.data.di.internal

import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.data.internal.repository.CollectionRoomRepository
import org.koin.dsl.module

val collectionRoomRepositoryModule = module {
    single<CollectionLocalRepository> { CollectionRoomRepository(get()) }
}
