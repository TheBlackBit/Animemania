package com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse

data class PosterImage(
    val large: String?,
    val medium: String?,
    val original: String?,
    val small: String?,
    val tiny: String?,
)

fun PosterImage.validImage(): String {
    return large ?: medium ?: original ?: small ?: tiny ?: ""
}
