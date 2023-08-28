package com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse

data class EpisodeAttributes(
    val canonicalTitle: String?,
    val number: Int?,
    val seasonNumber: Int?,
    val synopsis: String?,
    val thumbnail: ThumbnailEpisode?
)
