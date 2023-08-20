package com.theblackbit.animemania.android

import android.app.Application
import com.theblackbit.animemania.android.data.di.external.animeEpisodesRepositoryModule
import com.theblackbit.animemania.android.data.di.external.animeRepositoryModule
import com.theblackbit.animemania.android.data.di.external.charactersRepositoryModule
import com.theblackbit.animemania.android.data.di.external.kitsuAnimeDataSourceModule
import com.theblackbit.animemania.android.data.di.external.kitsuChaptersDataSource
import com.theblackbit.animemania.android.data.di.external.kitsuCharacterDataSource
import com.theblackbit.animemania.android.data.di.external.kitsuEpisodesDataSource
import com.theblackbit.animemania.android.data.di.external.kitsuMangaDataSourceModule
import com.theblackbit.animemania.android.data.di.external.loggingInterceptorModule
import com.theblackbit.animemania.android.data.di.external.mangaChaptersRepositoryModule
import com.theblackbit.animemania.android.data.di.external.mangaRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.chapterDaoModule
import com.theblackbit.animemania.android.data.di.internal.chapterRoomRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.characterDaoModule
import com.theblackbit.animemania.android.data.di.internal.characterRoomRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.collectionDaoModule
import com.theblackbit.animemania.android.data.di.internal.collectionRoomRepositoryModule
import com.theblackbit.animemania.android.data.di.internal.roomDbModule
import com.theblackbit.animemania.android.data.di.pagingsource.animePagingSourceFactoryModule
import com.theblackbit.animemania.android.data.di.pagingsource.chapterPagingSourceFactoryModule
import com.theblackbit.animemania.android.data.di.pagingsource.characterFactoryPagingSourceModule
import com.theblackbit.animemania.android.data.di.pagingsource.mangaPagingSourceFactoryModule
import com.theblackbit.animemania.android.detail.di.chapterTabViewModelModule
import com.theblackbit.animemania.android.detail.di.characterTabViewModelModule
import com.theblackbit.animemania.android.domain.di.collectAnimeUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectChaptersUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectCharactersUseCaseModule
import com.theblackbit.animemania.android.domain.di.collectMangaUseCaseModule
import com.theblackbit.animemania.android.home.di.homeViewModelModule
import com.theblackbit.animemania.android.util.di.safeApiRequestModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Animemania : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Animemania)

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
                    animeEpisodesRepositoryModule,
                    mangaChaptersRepositoryModule,
                    charactersRepositoryModule,
                    roomDbModule,
                    chapterDaoModule,
                    collectionDaoModule,
                    characterDaoModule,
                    collectionRoomRepositoryModule,
                    chapterRoomRepositoryModule,
                    characterRoomRepositoryModule,
                    animePagingSourceFactoryModule,
                    mangaPagingSourceFactoryModule,
                    characterFactoryPagingSourceModule,
                    chapterPagingSourceFactoryModule,
                    collectAnimeUseCaseModule,
                    collectMangaUseCaseModule,
                    collectChaptersUseCaseModule,
                    collectCharactersUseCaseModule,
                    homeViewModelModule,
                    chapterTabViewModelModule,
                    characterTabViewModelModule
                )
            )
        }
    }
}
