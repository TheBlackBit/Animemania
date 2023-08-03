package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuCharacterDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterData
import io.reactivex.rxjava3.core.Single

class CharactersByKitsuRepositoryImpl(
    private val kitsuCharacterDataSource: KitsuCharacterDataSource,
) : CharacterByKitsuRepository {
    override fun getCollectionCharacters(
        mediaType: String,
        collectionId: String,
        pageNumber: String,
        pageOffset: String,
    ): Single<CharacterData> {
        return kitsuCharacterDataSource.getCollectionCharacters(
            mediaType = mediaType,
            collectionId = collectionId,
            pageNumber = pageNumber,
            pageOffset = pageOffset,
        )
    }
}
