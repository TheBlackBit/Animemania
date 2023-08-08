package com.theblackbit.animemania.android.data.internal.datasource.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey
    val characterId: String,
    val name: String,
    val imageUrl: String,
    val collectionId: Int,
    val overView: String,
)
