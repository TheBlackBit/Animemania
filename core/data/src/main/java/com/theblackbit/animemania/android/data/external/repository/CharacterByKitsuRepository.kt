package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterData
import io.reactivex.rxjava3.core.Single

interface CharacterByKitsuRepository {

    fun getCollectionCharacters(
        mediaType: String,
        collectionId: String,
        pageNumber: String,
        pageOffset: String,
    ): Single<CharacterData>
}
