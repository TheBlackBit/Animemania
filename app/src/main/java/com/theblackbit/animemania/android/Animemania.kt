package com.theblackbit.animemania.android

import android.app.Application
import com.theblackbit.animemania.android.detail.di.chapterTabViewModelModule
import com.theblackbit.animemania.android.domain.di.collectAnimeCategoriesUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectAnimeUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectChaptersUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectCharactersUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectMangaCategoriesUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectMangaUseCaseModule
import com.theblackbit.animemania.android.home.di.homeViewModelModule
import org.koin.core.context.GlobalContext.startKoin

class Animemania : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    collectAnimeUseCaseModule,
                    collectMangaUseCaseModule,
                    collectAnimeCategoriesUseCaseModule,
                    collectMangaCategoriesUseCaseModule,
                    collectChaptersUseCaseModule,
                    collectCharactersUseCaseModule,
                    homeViewModelModule,
                    chapterTabViewModelModule,
                    chapterTabViewModelModule,
                ),
            )
        }
    }
}
