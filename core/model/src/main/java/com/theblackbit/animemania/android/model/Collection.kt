package com.theblackbit.animemania.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collection(
    val collectionId: String,
    val name: String,
    val averageRating: String,
    val startDate: String,
    val endDate: String,
    val genre: List<Genre>,
    val collectionType: CollectionType,
    val episodeCount: String,
    val miniPosterImageUrl: String,
    val bigPosterImageUrl: String,
    val status: String,
    val synopsis: String
) : Parcelable
