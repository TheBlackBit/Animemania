package com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse

import com.google.gson.annotations.SerializedName

data class CollectionResponse(
    @SerializedName("data")
    val collectionData: List<Data>,
)
