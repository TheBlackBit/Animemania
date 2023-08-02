package com.theblackbit.animemania.android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val name: String,
) : Parcelable
