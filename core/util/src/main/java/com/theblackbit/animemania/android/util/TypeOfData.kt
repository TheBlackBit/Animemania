package com.theblackbit.animemania.android.util

const val KEYWORD_ANIME = "anime"
const val KEYWORD_MANGA = "manga"

enum class TypeOfData(val code: Int, val keyword: String) {
    ANIME(0, KEYWORD_ANIME), MANGA(1, KEYWORD_MANGA)
}
