package com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse

import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CharacterEntity

data class CharacterData(
    val attributes: CharacterAttributes?,
    val id: String?,
    val type: String?,
)

// TODO: ADD UNIT TEST
fun CharacterData.toCharacterEntity(page: Int, collectionId: Int): CharacterEntity {
    return CharacterEntity(
        characterId = id?.toIntOrNull() ?: -1,
        name = attributes?.name ?: "",
        imageUrl = attributes?.image?.original ?: "",
        collectionId = collectionId,
        overView = attributes?.description ?: "",
        page = page,
    )
}
