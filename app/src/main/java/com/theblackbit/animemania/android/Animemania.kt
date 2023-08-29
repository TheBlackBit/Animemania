package com.theblackbit.animemania.android

import android.app.Application
import com.theblackbit.animemania.android.data.di.external.animeEpisodesRepositoryModule
import com.theblackbit.animemania.android.data.di.external.animeRepositoryModule
import com.theblackbit.animemania.android.data.di.external.charactersRepositoryModule
import com.theblackbit.animemania.android.data.di.external.kitsuAnimeDataSourceModule
import com.theblackbit.animemania.android.data.di.external.kitsuChaptersDataSourceModule
import com.theblackbit.animemania.android.data.di.external.kitsuCharacterDataSourceModule
import com.theblackbit.animemania.android.data.di.external.kitsuEpisodesDataSourceModule
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
import org.koin.core.module.Module

class Animemania : Application() {

    private val viewModelModules = listOf(
        homeViewModelModule,
        chapterTabViewModelModule,
        characterTabViewModelModule
    )

    private val remoteRepositoryModules = listOf(
        animeRepositoryModule,
        mangaRepositoryModule,
        animeEpisodesRepositoryModule,
        mangaChaptersRepositoryModule,
        charactersRepositoryModule
    )

    private val localRepositoryModules = listOf(
        collectionRoomRepositoryModule,
        chapterRoomRepositoryModule,
        characterRoomRepositoryModule
    )

    private val pagingSourceModule = listOf(
        animePagingSourceFactoryModule,
        mangaPagingSourceFactoryModule,
        characterFactoryPagingSourceModule,
        chapterPagingSourceFactoryModule
    )

    private val useCaseModules = listOf(
        collectAnimeUseCaseModule,
        collectMangaUseCaseModule,
        collectChaptersUseCaseModule,
        collectCharactersUseCaseModule
    )

    private val dataModules = listOf(
        safeApiRequestModule,
        loggingInterceptorModule,
        kitsuAnimeDataSourceModule,
        kitsuMangaDataSourceModule,
        kitsuEpisodesDataSourceModule,
        kitsuChaptersDataSourceModule,
        kitsuCharacterDataSourceModule,
        roomDbModule,
        chapterDaoModule,
        collectionDaoModule,
        characterDaoModule
    )

    override fun onCreate() {
        super.onCreate()

        val modules: MutableList<Module> = ArrayList()

        modules.addAll(dataModules)
        modules.addAll(remoteRepositoryModules)
        modules.addAll(localRepositoryModules)
        modules.addAll(pagingSourceModule)
        modules.addAll(useCaseModules)
        modules.addAll(viewModelModules)

        startKoin {
            androidContext(this@Animemania)
            modules(
                modules
            )
        }
    }
}
