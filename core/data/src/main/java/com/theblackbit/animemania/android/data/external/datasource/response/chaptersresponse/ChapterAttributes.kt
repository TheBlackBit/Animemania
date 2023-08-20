package com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse

data class ChapterAttributes(
    val canonicalTitle: String?,
    val createdAt: String?,
    val length: Int?,
    val number: Int?,
    val volumeNumber: Int?,
    val synopsis: String?,
    val updatedAt: String?,
    val thumbnail: ThumbnailChapter?
)
