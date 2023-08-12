package com.theblackbit.animemania.android.data.external.datasource.categories

import com.theblackbit.animemania.android.model.Category

data class Categories(
    val id: Int,
    val name: String,
    val collectionType: CollectionType,
)

// TODO: ADD UNIT TEST and rename

fun Categories.toCategory(): Category {
    return Category(
        categoryId = id,
        categoryName = name,
    )
}
