package com.theblackbit.animemania.android.data.di.internal

import com.theblackbit.animemania.android.data.internal.repository.CharacterLocalRepository
import com.theblackbit.animemania.android.data.internal.repository.CharacterRoomRepository
import org.koin.dsl.module

val characterRoomRepositoryModule = module {
    single<CharacterLocalRepository> { CharacterRoomRepository(get()) }
}
