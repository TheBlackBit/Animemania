package com.theblackbit.animemania.android.data.di.external

import com.google.gson.GsonBuilder
import com.theblackbit.animemania.android.core.data.BuildConfig
import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuCharacterDataSource
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val kitsuCharacterDataSourceModule = module {
    single<KitsuCharacterDataSource> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.KITSU_API_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(get())
            .build()
            .create(KitsuCharacterDataSource::class.java)
    }
}
