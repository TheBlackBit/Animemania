package com.theblackbit.animemania.android.util.di

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class SafeApiRequestModuleTest : KoinTest {

    @Test
    fun checkModule() {
        koinApplication {
            modules(safeApiRequestModule)
        }.checkModules()
    }
}
