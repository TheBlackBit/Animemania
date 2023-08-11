package com.theblackbit.animemania.android.data.di.external

import com.theblackbit.animemania.android.data.external.repository.MangaByKitsuRemoteRepository
import org.koin.dsl.module

val mangaRepositoryModule = module {
    single { MangaByKitsuRemoteRepository(get(), get()) }
}
