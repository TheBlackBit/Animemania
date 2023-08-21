package com.theblackbit.animemania.android.data.di.external

import com.google.gson.GsonBuilder
import com.theblackbit.animemania.android.core.data.BuildConfig
import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuEpisodesDataSource
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val kitsuEpisodesDataSource = module {
    single<KitsuEpisodesDataSource> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.KITSU_API_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(get())
            .build()
            .create(KitsuEpisodesDataSource::class.java)
    }
}
