package com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse

import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionEntity

data class Data(
    val attributes: Attributes?,
    val id: String?,
    val type: String?
)

fun Data.toCollectionEntity(
    collectionType: String,
    typeOfRequest: RequestType,
    page: Int
): CollectionEntity {
    return CollectionEntity(
        collectionId = id ?: "-1",
        name = attributes?.canonicalTitle ?: "",
        averageRating = (attributes?.averageRating?.plus("%")) ?: "",
        startDate = attributes?.startDate ?: "",
        endDate = attributes?.endDate ?: "",
        genres = "",
        collectionType = collectionType,
        episodeCount = "",
        miniPosterImageUrl = attributes?.posterImage?.validImage() ?: "",
        bigPosterImageUrl = attributes?.coverImage?.validImage() ?: "",
        status = attributes?.status ?: "",
        synopsis = attributes?.synopsis ?: "",
        typeOfRequest = typeOfRequest.name,
        page = page
    )
}
