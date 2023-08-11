package com.theblackbit.animemania.android.data.external.datasource.kitsuapi

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface KitsuAnimeDataSource {
    @GET("trending/Anime")
    fun getTrendingCollection(
        @Query("include") include: String = KitsuInclude.CATEGORIES,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String?,
    ): Single<Any>

    @GET("Anime")
    fun getMostWantedCollection(
        @Query("filter[status]") filter: String = KitsuFilter.UPCOMING,
        @Query("sort") sort: String = KitsuSort.USER_COUNT_DES,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String?,
    ): Single<Any>

    @GET("Anime")
    fun getTopRatedCollection(
        @Query("sort") sort: String = KitsuSort.AVERAGE_RATING,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String?,
    ): Single<Any>

    @GET("Anime")
    fun getPopularCollection(
        @Query("sort") sort: String = KitsuSort.USER_COUNT_DES,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String?,
    ): Single<Any>
}
