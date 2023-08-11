package com.theblackbit.animemania.android.data.di.internal

import com.theblackbit.animemania.android.data.internal.datasource.room.AnimeManiaRoom
import org.koin.dsl.module

val chapterLocalRepositoryModule = module {
    single {
        val db = get<AnimeManiaRoom>()
        db.chapterDao()
    }
}
