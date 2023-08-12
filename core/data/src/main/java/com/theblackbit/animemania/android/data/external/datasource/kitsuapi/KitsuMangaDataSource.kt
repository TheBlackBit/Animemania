package com.theblackbit.animemania.android.data.external.datasource.kitsuapi

import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface KitsuMangaDataSource {
    @GET("trending/Manga")
    fun getTrendingCollection(
        @Query("include") include: String = KitsuInclude.CATEGORIES,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String?,
    ): Single<CollectionResponse>

    @GET("manga")
    fun getMostWantedCollection(
        @Query("filter[status]") filter: String = KitsuFilter.UPCOMING,
        @Query("sort") sort: String = KitsuSort.USER_COUNT_DES,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String?,
    ): Single<CollectionResponse>

    @GET("manga")
    fun getTopRatedCollection(
        @Query("sort") sort: String = KitsuSort.AVERAGE_RATING,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String?,
    ): Single<CollectionResponse>

    @GET("manga")
    fun getPopularCollection(
        @Query("sort") sort: String = KitsuSort.USER_COUNT_DES,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String?,
    ): Single<CollectionResponse>
}
