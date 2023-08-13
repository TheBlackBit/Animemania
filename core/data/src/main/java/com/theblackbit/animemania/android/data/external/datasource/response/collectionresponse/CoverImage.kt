package com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse

data class CoverImage(
    val large: String?,
    val original: String?,
    val small: String?,
    val tiny: String?,
    val medium: String?,
)

// TODO: ADD UNIT TEST
fun CoverImage.validImage(): String {
    return large ?: medium ?: original ?: small ?: tiny ?: ""
}
