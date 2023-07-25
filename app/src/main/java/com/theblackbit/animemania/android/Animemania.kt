package com.theblackbit.animemania.android

import android.app.Application
import com.theblackbit.animemania.android.domain.di.useCaseModule
import com.theblackbit.animemania.android.home.di.homeViewModelModule
import org.koin.core.context.GlobalContext.startKoin

class Animemania : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            useCaseModule
            homeViewModelModule
        }
    }
}
