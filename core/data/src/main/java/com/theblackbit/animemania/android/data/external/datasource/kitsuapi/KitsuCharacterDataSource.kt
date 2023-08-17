package com.theblackbit.animemania.android.data.external.datasource.kitsuapi

import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface KitsuCharacterDataSource {
    @GET("castings")
    fun getCollectionCharacters(
        @Query("filter[media_type]") mediaType: String,
        @Query("filter[media_id]") collectionId: String,
        @Query("include") include: String = KitsuInclude.CHARACTER,
        @Query("filter[is_character]") isCharacter: Boolean = true,
        @Query("page[limit]") pageNumber: String,
        @Query("page[offset]") pageOffset: String?,
    ): Single<CharacterResponse>
}
