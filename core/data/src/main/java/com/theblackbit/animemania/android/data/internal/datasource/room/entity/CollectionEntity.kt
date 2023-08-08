package com.theblackbit.animemania.android.data.internal.datasource.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CollectionEntity(
    @PrimaryKey
    val collectionId: Int,
    val name: String,
    val averageRating: String,
    val startDate: String,
    val endDate: String,
    val genres: String,
    val categoryId: Int,
    val collectionType: String,
    val episodeCount: String,
    val miniPosterImageUrl: String,
    val bigPosterImageUrl: String,
    val status: String,
    val synopsis: String,
)
