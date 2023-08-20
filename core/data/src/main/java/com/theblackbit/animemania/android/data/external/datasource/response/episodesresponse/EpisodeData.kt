package com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse

import com.theblackbit.animemania.android.data.internal.datasource.room.entity.ChapterEntity

data class EpisodeData(
    val attributes: EpisodeAttributes?,
    val id: String?,
    val type: String?
)

fun EpisodeData.toChapterEntity(page: Int, collectionId: String): ChapterEntity {
    return ChapterEntity(
        chapterId = id ?: "-1",
        title = attributes?.canonicalTitle ?: "",
        synopsis = attributes?.synopsis ?: "",
        collectionId = collectionId,
        number = attributes?.number ?: -1,
        seasonNumber = attributes?.seasonNumber ?: -1,
        imageUrl = attributes?.thumbnail?.validImage() ?: "",
        pageNumber = page
    )
}
