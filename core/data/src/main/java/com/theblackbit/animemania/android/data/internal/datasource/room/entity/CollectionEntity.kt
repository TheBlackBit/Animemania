package com.theblackbit.animemania.android.data.internal.datasource.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.model.CollectionType
import com.theblackbit.animemania.android.model.StatusOfEmission

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

// TODO: REPLACE dates and GENRE AND TEST
fun CollectionEntity.toCollection(): Collection {
    return Collection(
        collectionId = collectionId.toString(),
        name = name,
        averageRating = averageRating,
        startDate = startDate,
        endDate = endDate,
        genre = emptyList(),
        collectionType = CollectionType.ANIME,
        episodeCount = episodeCount,
        miniPosterImageUrl = miniPosterImageUrl,
        bigPosterImageUrl = bigPosterImageUrl,
        status = StatusOfEmission.CURRENT,
        synopsis = synopsis,
    )
}
