package com.theblackbit.animemania.android.data.external.datasource.categories

const val TRENDING_MANGA_ID = 5
const val MOST_ANTICIPATED_MANGA_ID = 6
const val TOP_RATED_MANGA_ID = 7
const val POPULAR_MANGA_ID = 8
val mangaCategories = listOf(
    Categories(
        id = TRENDING_MANGA_ID,
        name = "Trending",
        collectionType = CollectionType.MANGA,
    ),
    Categories(
        id = MOST_ANTICIPATED_MANGA_ID,
        name = "Most Anticipated",
        collectionType = CollectionType.MANGA,
    ),
    Categories(
        id = TOP_RATED_MANGA_ID,
        name = "Top Rated",
        collectionType = CollectionType.MANGA,
    ),
    Categories(
        id = POPULAR_MANGA_ID,
        name = "Popular",
        collectionType = CollectionType.MANGA,
    ),
)
