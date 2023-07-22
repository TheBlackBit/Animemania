package com.theblackbit.animemania.android.model

data class Collection(
    val collectionId: String,
    val name: String,
    val averageRating: String,
    val startDate: String,
    val endDate: String,
    val episodeCount: String,
    val miniPosterImageUrl: String,
    val bigPosterImageUrl: String,
    val status: String,
    val overview: String,
)
