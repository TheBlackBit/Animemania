package com.theblackbit.animemania.android.data.di.internal

import androidx.room.Room
import com.theblackbit.animemania.android.data.internal.datasource.room.AnimeManiaRoom
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomDbModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AnimeManiaRoom::class.java,
            name = "animemania_db"
        ).build()
    }
}
