package com.theblackbit.animemania.android.data.di.internal

import com.theblackbit.animemania.android.data.internal.repository.ChapterLocalRepository
import com.theblackbit.animemania.android.data.internal.repository.ChapterRoomRepository
import org.koin.dsl.module

val chapterRoomRepositoryModule = module {
    single<ChapterLocalRepository> { ChapterRoomRepository(get()) }
}
