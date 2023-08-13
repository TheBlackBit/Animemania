package com.theblackbit.animemania.android.data.external.datasource.categories

import com.theblackbit.animemania.android.model.Category

data class CategoryData(
    val id: Int,
    val name: String,
    val collectionType: CollectionType,
)

fun CategoryData.toCategory(): Category {
    return Category(
        categoryId = id,
        categoryName = name,
    )
}
