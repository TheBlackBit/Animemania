package com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse

import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionEntity

data class Data(
    val attributes: Attributes?,
    val id: String?,
    val type: String?,
)

// TODO:REPLACE GENRES URL EPISODECOUNT AND TEST add toSafeInt
fun Data.toCollectionEntity(categoryId: Int, collectionType: String): CollectionEntity {
    return CollectionEntity(
        collectionId = id ?: "-1",
        name = attributes?.canonicalTitle ?: "",
        averageRating = (attributes?.averageRating?.plus("%")) ?: "",
        startDate = attributes?.startDate ?: "",
        endDate = attributes?.endDate ?: "",
        genres = "",
        categoryId = categoryId,
        collectionType = collectionType,
        episodeCount = "",
        miniPosterImageUrl = attributes?.posterImage?.validImage() ?: "",
        bigPosterImageUrl = attributes?.coverImage?.validImage() ?: "",
        status = attributes?.status ?: "",
        synopsis = attributes?.synopsis ?: "",
    )
}
