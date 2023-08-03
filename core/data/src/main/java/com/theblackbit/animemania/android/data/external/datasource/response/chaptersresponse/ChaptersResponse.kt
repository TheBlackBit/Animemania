package com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse

import com.google.gson.annotations.SerializedName

data class ChaptersResponse(
    @SerializedName("data")
    val chapterData: List<ChapterData>,
)
