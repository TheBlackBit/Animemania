package com.theblackbit.animemania.android

import android.app.Application
import com.theblackbit.animemania.android.data.di.external.animeCategoriesRepositoryModule
import com.theblackbit.animemania.android.data.di.external.animeEpisodesRepositoryModule
import com.theblackbit.animemania.android.data.di.external.animeRepositoryModule
import com.theblackbit.animemania.android.data.di.external.charactersRepositoryModule
import com.theblackbit.animemania.android.data.di.external.kitsuAnimeDataSourceModule
import com.theblackbit.animemania.android.data.di.external.kitsuChaptersDataSource
import com.theblackbit.animemania.android.data.di.external.kitsuCharacterDataSource
import com.theblackbit.animemania.android.data.di.external.kitsuEpisodesDataSource
import com.theblackbit.animemania.android.data.di.external.kitsuMangaDataSourceModule
import com.theblackbit.animemania.android.data.di.external.loggingInterceptorModule
import com.theblackbit.animemania.android.data.di.external.mangaCategoriesRepositoryModule
import com.theblackbit.animemania.android.data.di.external.mangaChaptersRepositoryModule
import com.theblackbit.animemania.android.data.di.external.mangaRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.categoryLocalRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.chapterLocalRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.characterLocalRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.collectionLocalRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.roomDbModule
import com.theblackbit.animemania.android.detail.di.chapterTabViewModelModule
import com.theblackbit.animemania.android.domain.di.collectAnimeCategoriesUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectAnimeUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectChaptersUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectCharactersUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectMangaCategoriesUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectMangaUseCaseModule
import com.theblackbit.animemania.android.home.di.homeViewModelModule
import com.theblackbit.animemania.android.util.di.safeApiRequestModule
import org.koin.core.context.GlobalContext.startKoin

class Animemania : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    safeApiRequestModule,
                    loggingInterceptorModule,
                    kitsuAnimeDataSourceModule,
                    kitsuMangaDataSourceModule,
                    kitsuEpisodesDataSource,
                    kitsuChaptersDataSource,
                    kitsuCharacterDataSource,
                    animeRepositoryModule,
                    mangaRepositoryModule,
                    mangaCategoriesRepositoryModule,
                    animeCategoriesRepositoryModule,
                    animeEpisodesRepositoryModule,
                    mangaChaptersRepositoryModule,
                    charactersRepositoryModule,
                    roomDbModule,
                    categoryLocalRepositoryModule,
                    chapterLocalRepositoryModule,
                    collectionLocalRepositoryModule,
                    characterLocalRepositoryModule,
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
