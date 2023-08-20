package com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse

import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CharacterEntity

data class CharacterData(
    val attributes: CharacterAttributes?,
    val id: String?,
    val type: String?
)

fun CharacterData.toCharacterEntity(page: Int, collectionId: String): CharacterEntity {
    return CharacterEntity(
        characterId = id ?: "-1",
        name = attributes?.canonicalName ?: "",
        imageUrl = attributes?.image?.validImage() ?: "",
        collectionId = collectionId,
        overView = attributes?.description ?: "",
        page = page
    )
}
