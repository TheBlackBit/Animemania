package com.theblackbit.animemania.android.data.internal.di

import android.content.Context
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
import com.theblackbit.animemania.android.util.di.safeApiRequestModule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestModules : KoinTest {

    @Mock
    private lateinit var context: Context

    @Test
    fun checkModule() {
        val modules = listOf(
            safeApiRequestModule,
            animeEpisodesRepositoryModule,
            animeRepositoryModule,
            charactersRepositoryModule,
            kitsuAnimeDataSourceModule,
            kitsuCharacterDataSourceModule,
            kitsuEpisodesDataSourceModule,
            kitsuChaptersDataSourceModule,
            kitsuMangaDataSourceModule,
            loggingInterceptorModule,
            mangaChaptersRepositoryModule,
            mangaRepositoryModule,
            chapterDaoModule,
            chapterRoomRepositoryModule,
            characterDaoModule,
            characterRoomRepositoryModule,
            collectionDaoModule,
            collectionRoomRepositoryModule,
            roomDbModule,
            animePagingSourceFactoryModule,
            mangaPagingSourceFactoryModule,
            chapterPagingSourceFactoryModule,
            characterFactoryPagingSourceModule
        )
        koinApplication {
            androidContext(context)
            modules(modules)
        }.checkModules()
    }
}
