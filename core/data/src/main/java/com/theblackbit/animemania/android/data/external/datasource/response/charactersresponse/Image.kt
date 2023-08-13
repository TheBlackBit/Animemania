package com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse

data class Image(
    val original: String?,
    val large: String?,
    val medium: String?,
    val small: String?,
    val tiny: String?,
)

fun Image.validImage(): String {
    return original ?: medium ?: large ?: small ?: tiny ?: ""
}
