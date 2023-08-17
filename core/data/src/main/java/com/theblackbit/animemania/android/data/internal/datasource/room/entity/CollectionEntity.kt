package com.theblackbit.animemania.android.data.internal.datasource.room.entity

import androidx.room.Entity
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.model.CollectionType

@Entity(
    primaryKeys = ["collectionId", "typeOfRequest"],
)
data class CollectionEntity(
    val collectionId: String,
    val name: String,
    val averageRating: String,
    val startDate: String,
    val endDate: String,
    val genres: String,
    val typeOfRequest: String,
    val collectionType: String,
    val episodeCount: String,
    val miniPosterImageUrl: String,
    val bigPosterImageUrl: String,
    val status: String,
    val synopsis: String,
    val page: Int,
)

fun CollectionEntity.toCollection(collectionType: CollectionType): Collection {
    return Collection(
        collectionId = collectionId,
        name = name,
        averageRating = averageRating,
        startDate = startDate,
        endDate = endDate,
        genre = emptyList(),
        collectionType = collectionType,
        episodeCount = episodeCount,
        miniPosterImageUrl = miniPosterImageUrl,
        bigPosterImageUrl = bigPosterImageUrl,
        status = status,
        synopsis = synopsis,
    )
}
