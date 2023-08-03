package com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data")
    val characterData: List<CharacterData>,
)
