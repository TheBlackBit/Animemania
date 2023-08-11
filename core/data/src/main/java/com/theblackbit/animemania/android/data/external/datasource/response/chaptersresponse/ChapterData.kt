package com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse

import com.theblackbit.animemania.android.data.internal.datasource.room.entity.ChapterEntity

data class ChapterData(
    val attributes: ChapterAttributes?,
    val id: String?,
    val type: String?,
)

fun ChapterData.toChapterEntity(page: Int, collectionId: Int): ChapterEntity {
    return ChapterEntity(
        chapterId = id?.toIntOrNull() ?: -1,
        title = attributes?.canonicalTitle ?: "",
        synopsis = attributes?.synopsis ?: "",
        collectionId = collectionId,
        number = attributes?.number ?: -1,
        seasonNumber = attributes?.volumeNumber ?: -1,
        imageUrl = attributes?.thumbnail?.medium ?: "",
        pageNumber = page,
    )
}
