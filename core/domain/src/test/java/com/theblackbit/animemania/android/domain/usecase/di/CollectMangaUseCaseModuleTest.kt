package com.theblackbit.animemania.android.domain.usecase.di

import com.theblackbit.animemania.android.data.pagingsource.collection.manga.MangaPagingSourceFactory
import com.theblackbit.animemania.android.domain.di.collectMangaUseCaseModule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CollectMangaUseCaseModuleTest : KoinTest {

    @Mock
    private lateinit var pagingSourceFactory: MangaPagingSourceFactory

    @Test
    fun checkModule() {
        val pagingSourceFactoryModule = module {
            factory { pagingSourceFactory }
        }

        val modules = listOf(
            pagingSourceFactoryModule,
            collectMangaUseCaseModule
        )
        koinApplication {
            modules(modules)
        }.checkModules()
    }
}
