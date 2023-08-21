package com.theblackbit.animemania.android.data.di.external

import com.theblackbit.animemania.android.data.external.repository.MangaByKitsuRemoteRepository
import com.theblackbit.animemania.android.data.external.repository.MangaRemoteRepository
import org.koin.dsl.module

val mangaRepositoryModule = module {
    single<MangaRemoteRepository> { MangaByKitsuRemoteRepository(get(), get()) }
}
