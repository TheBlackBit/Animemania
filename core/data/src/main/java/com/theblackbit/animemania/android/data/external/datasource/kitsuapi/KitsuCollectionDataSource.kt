package com.theblackbit.animemania.android.data.external.datasource.kitsuapi

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuFilter.UPCOMING
import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuInclude.CATEGORIES
import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuSort.AVERAGE_RATING
import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuSort.USER_COUNT_DES
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KitsuCollectionDataSource {
    @GET("trending/{path}")
    fun getTrendingCollection(
        @Path("path") path: String,
        @Query("include") include: String = CATEGORIES,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String,
    ): Single<CollectionResponse>

    @GET("{path}")
    fun getMostWantedCollection(
        @Path("path") path: String,
        @Query("filter[status]") filter: String = UPCOMING,
        @Query("sort") sort: String = USER_COUNT_DES,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String,
    ): Single<CollectionResponse>

    @GET("{path}")
    fun getTopRatedCollection(
        @Path("path") path: String,
        @Query("sort") sort: String = AVERAGE_RATING,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String,
    ): Single<CollectionResponse>

    @GET("{path}")
    fun getPopularCollection(
        @Path("path") path: String,
        @Query("sort") sort: String = USER_COUNT_DES,
        @Query("page[limit]") pageLimit: String,
        @Query("page[offset]") pageOffset: String,
    ): Single<CollectionResponse>
}
