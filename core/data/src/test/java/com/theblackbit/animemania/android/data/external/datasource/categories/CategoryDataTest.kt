package com.theblackbit.animemania.android.data.external.datasource.categories

import org.junit.Assert
import org.junit.Test

class CategoryDataTest {

    @Test
    fun testCategoryDataToCategoryModelWithAnimeType() {
        val id = 1
        val name = "test"
        val collectionType = CollectionType.ANIME
        val categoryData = CategoryData(
            id = id,
            name = name,
            collectionType = collectionType,
        )

        val category = categoryData.toCategory()

        Assert.assertEquals(category.categoryId, categoryData.id)
        Assert.assertEquals(category.categoryName, categoryData.name)
    }
}
