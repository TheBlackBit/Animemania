package com.theblackbit.animemania.android.data.external.datasource.kitsuapi

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface KitsuChaptersDataSource {
    @GET("chapters")
    fun getChapters(
        @Query("filter[manga_id]") mangaId: String,
        @Query("sort") sort: String = KitsuSort.NUMBER,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String,
    ): Single<Any>
}
