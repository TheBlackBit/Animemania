package com.theblackbit.animemania.android.data.internal.datasource.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.theblackbit.animemania.android.model.Character

@Entity
data class CharacterEntity(
    @PrimaryKey
    val characterId: String,
    val name: String,
    val imageUrl: String,
    val collectionId: String,
    val overView: String,
    val page: Int,
)

// TODO: ADD UNIT TEST
fun CharacterEntity.toCharacterModel(): Character {
    return Character(
        id = characterId,
        name = name,
        imageUrl = imageUrl,
        overView = overView,
    )
}
