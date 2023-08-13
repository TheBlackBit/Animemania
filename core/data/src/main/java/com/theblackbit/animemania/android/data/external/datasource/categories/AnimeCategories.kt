package com.theblackbit.animemania.android.data.external.datasource.categories

const val TRENDING_ANIME_ID = 1
const val MOST_ANTICIPATED_ANIME_ID = 2
const val TOP_RATED_ANIME_ID = 3
const val POPULAR_ANIME_ID = 4
val animeCategories = listOf(
    CategoryData(
        id = TRENDING_ANIME_ID,
        name = "Trending",
        collectionType = CollectionType.ANIME,
    ),
    CategoryData(
        id = MOST_ANTICIPATED_ANIME_ID,
        name = "Most Anticipated",
        collectionType = CollectionType.ANIME,
    ),
    CategoryData(
        id = TOP_RATED_ANIME_ID,
        name = "Top Rated",
        collectionType = CollectionType.ANIME,
    ),
    CategoryData(
        id = POPULAR_ANIME_ID,
        name = "Popular",
        collectionType = CollectionType.ANIME,
    ),
)
