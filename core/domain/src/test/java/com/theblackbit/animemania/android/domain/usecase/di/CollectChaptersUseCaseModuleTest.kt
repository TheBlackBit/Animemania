package com.theblackbit.animemania.android.domain.usecase.di

import com.theblackbit.animemania.android.data.pagingsource.chapter.ChapterPagingSourceFactory
import com.theblackbit.animemania.android.domain.di.collectChaptersUseCaseModule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CollectChaptersUseCaseModuleTest : KoinTest {

    @Mock
    private lateinit var chapterPagingSourceFactory: ChapterPagingSourceFactory

    @Test
    fun checkModule() {
        val chapterPagingSourceFactoryModule = module {
            factory { chapterPagingSourceFactory }
        }

        val modules = listOf(
            chapterPagingSourceFactoryModule,
            collectChaptersUseCaseModule
        )
        koinApplication {
            modules(modules)
        }.checkModules()
    }
}
