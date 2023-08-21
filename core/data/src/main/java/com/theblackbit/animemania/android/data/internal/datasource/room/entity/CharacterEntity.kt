package com.theblackbit.animemania.android.data.internal.datasource.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.theblackbit.animemania.android.model.Character

@Entity
data class CharacterEntity(
    val characterId: String,
    @PrimaryKey
    val name: String,
    val imageUrl: String,
    val collectionId: String,
    val overView: String,
    val page: Int
)

fun CharacterEntity.toCharacterModel(): Character {
    return Character(
        id = characterId,
        name = name,
        imageUrl = imageUrl,
        overView = overView
    )
}
