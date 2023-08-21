package com.theblackbit.animemania.android.data.external.datasource.kitsuapi

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuSort.NUMBER
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface KitsuEpisodesDataSource {
    @GET("episodes")
    fun getEpisodes(
        @Query("filter[mediaType]") mediaType: String = KitsuMediaType.ANIME_MEDIA_TYPE,
        @Query("filter[media_id]") collectionId: String,
        @Query("sort") sort: String = NUMBER,
        @Query("page[limit]") pageNumber: String,
        @Query("page[offset]") pageOffset: String
    ): Single<EpisodesResponse>
}
