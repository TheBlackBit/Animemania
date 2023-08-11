package com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse

data class EpisodeAttributes(
    val canonicalTitle: String?,
    val createdAt: String?,
    val length: Int?,
    val number: Int?,
    val relativeNumber: Int?,
    val seasonNumber: Int?,
    val synopsis: String?,
    val updatedAt: String?,
    val thumbnail: ThumbnailEpisode?,
)
