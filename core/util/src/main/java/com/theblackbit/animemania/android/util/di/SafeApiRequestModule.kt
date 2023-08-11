package com.theblackbit.animemania.android.util.di

import com.theblackbit.animemania.android.util.SafeApiRequest
import org.koin.dsl.module

val safeApiRequestModule = module {
    single { SafeApiRequest() }
}
