package com.theblackbit.animemania.android.core.testing.data

import com.theblackbit.animemania.android.model.Category

const val CATEGORY_TRENDING = "Trending"
const val CATEGORY_MOST_WANTED = "Most wanted"
const val CATEGORY_TOP_RATED = "Top Rated"
const val CATEGORY_POPULAR = "Popular"

const val CATEGORY_TRENDING_ID = 1
const val CATEGORY_MOST_WANTED_ID = 2
const val CATEGORY_TOP_RATED_ID = 3
const val CATEGORY_POPULAR_ID = 4

val collectionCategories: List<Category> = listOf(
    Category(CATEGORY_TRENDING_ID, CATEGORY_TRENDING),
    Category(CATEGORY_MOST_WANTED_ID, CATEGORY_MOST_WANTED),
    Category(CATEGORY_TOP_RATED_ID, CATEGORY_TOP_RATED),
    Category(CATEGORY_POPULAR_ID, CATEGORY_POPULAR),
)
