package com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse

import com.google.gson.annotations.SerializedName

data class EpisodesResponse(
    @SerializedName("data")
    val episodeData: List<EpisodeData>
)
