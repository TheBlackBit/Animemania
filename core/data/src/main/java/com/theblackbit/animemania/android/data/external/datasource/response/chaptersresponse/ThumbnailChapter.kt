package com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse

data class ThumbnailChapter(
    val tiny: String?,
    val large: String?,
    val small: String?,
    val medium: String?,
    val original: String?
)

fun ThumbnailChapter.validImage(): String {
    return original ?: medium ?: large ?: small ?: tiny ?: ""
}
