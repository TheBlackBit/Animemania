package com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("included")
    val characterData: List<CharacterData>,
)
