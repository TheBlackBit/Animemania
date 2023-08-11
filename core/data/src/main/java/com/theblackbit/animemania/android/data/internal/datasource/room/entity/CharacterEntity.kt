package com.theblackbit.animemania.android.data.internal.datasource.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.theblackbit.animemania.android.model.Character

@Entity
data class CharacterEntity(
    @PrimaryKey
    val characterId: Int,
    val name: String,
    val imageUrl: String,
    val collectionId: Int,
    val overView: String,
    val page: Int,
)

// TODO: ADD UNIT TEST
fun CharacterEntity.toCharacterModel(): Character {
    return Character(
        id = characterId.toString(),
        name = name,
        imageUrl = imageUrl,
        overView = overView,
    )
}
