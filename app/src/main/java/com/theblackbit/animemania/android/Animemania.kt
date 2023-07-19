package com.theblackbit.animemania.android

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin

class Animemania : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
        }
    }
}
