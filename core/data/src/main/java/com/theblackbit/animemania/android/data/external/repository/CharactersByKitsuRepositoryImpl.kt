package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuCharacterDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single

class CharactersByKitsuRepositoryImpl(
    private val kitsuCharacterDataSource: KitsuCharacterDataSource,
    private val safeApiRequest: SafeApiRequest,
) : CharacterByKitsuRepository {
    override fun getCollectionCharacters(
        mediaType: String,
        collectionId: String,
        pageNumber: String,
        pageOffset: String?,
    ): Single<SafeApiRequest.ApiResultHandle<CharacterResponse>> {
        return safeApiRequest.request {
            kitsuCharacterDataSource.getCollectionCharacters(
                mediaType = mediaType,
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset,
            )
        }
    }
}
