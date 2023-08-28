package com.theblackbit.animemania.android.domain.usecase.di

import com.theblackbit.animemania.android.data.pagingsource.character.CharacterPagingSourceFactory
import com.theblackbit.animemania.android.domain.di.collectCharactersUseCaseModule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CollectCharactersUseCaseModuleTest : KoinTest {

    @Mock
    private lateinit var characterPagingSourceFactory: CharacterPagingSourceFactory

    @Test
    fun checkModule() {
        val pagingSourceFactoryModule = module {
            factory { characterPagingSourceFactory }
        }

        val modules = listOf(
            pagingSourceFactoryModule,
            collectCharactersUseCaseModule
        )
        koinApplication {
            modules(modules)
        }.checkModules()
    }
}
