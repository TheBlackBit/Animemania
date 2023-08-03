package com.theblackbit.animemania.android

import android.app.Application
import com.theblackbit.animemania.android.data.di.animeEpisodesRepositoryModule
import com.theblackbit.animemania.android.data.di.animeRepositoryModule
import com.theblackbit.animemania.android.data.di.charactersRepositoryModule
import com.theblackbit.animemania.android.data.di.kitsuChaptersDataSource
import com.theblackbit.animemania.android.data.di.kitsuCharacterDataSource
import com.theblackbit.animemania.android.data.di.kitsuCollectionDataSourceModule
import com.theblackbit.animemania.android.data.di.kitsuEpisodesDataSource
import com.theblackbit.animemania.android.data.di.loggingInterceptorModule
import com.theblackbit.animemania.android.data.di.mangaChaptersRepositoryModule
import com.theblackbit.animemania.android.data.di.mangaRepositoryModule
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
                    loggingInterceptorModule,
                    kitsuCollectionDataSourceModule,
                    kitsuEpisodesDataSource,
                    kitsuChaptersDataSource,
                    kitsuCharacterDataSource,
                    animeRepositoryModule,
                    mangaRepositoryModule,
                    animeEpisodesRepositoryModule,
                    mangaChaptersRepositoryModule,
                    charactersRepositoryModule,
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
