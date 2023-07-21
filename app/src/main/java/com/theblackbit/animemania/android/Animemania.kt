package com.theblackbit.animemania.android

import android.app.Application
import com.theblackbit.animemania.android.di.useCaseModule
import com.theblackbit.animemania.android.di.viewModelModule
import org.koin.core.context.GlobalContext.startKoin

class Animemania : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            useCaseModule
            viewModelModule
        }
    }
}
