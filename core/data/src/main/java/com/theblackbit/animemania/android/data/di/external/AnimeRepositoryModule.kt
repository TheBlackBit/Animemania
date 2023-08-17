package com.theblackbit.animemania.android.data.di.external

import com.theblackbit.animemania.android.data.external.repository.AnimeByKitsuRemoteRepository
import com.theblackbit.animemania.android.data.external.repository.AnimeRemoteRepository
import org.koin.dsl.module

val animeRepositoryModule = module {
    single<AnimeRemoteRepository> {
        AnimeByKitsuRemoteRepository(
            get(),
            get(),
        )
    }
}
