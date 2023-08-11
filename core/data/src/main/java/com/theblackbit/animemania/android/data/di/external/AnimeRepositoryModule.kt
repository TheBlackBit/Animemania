package com.theblackbit.animemania.android.data.di.external

import com.theblackbit.animemania.android.data.external.repository.AnimeByKitsuRemoteRepository
import org.koin.dsl.module

val animeRepositoryModule = module {
    single {
        AnimeByKitsuRemoteRepository(
            get(),
            get(),
        )
    }
}
