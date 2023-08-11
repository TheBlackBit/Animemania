package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.dao.CategoryDao
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CategoryEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryRoomRepositoryTest {

    @Mock
    lateinit var categoryDao: CategoryDao

    private lateinit var sut: CategoryRoomRepository

    @Before
    fun setUp() {
        sut = CategoryRoomRepository(categoryDao)
    }

    @Test
    fun testInsertCategoryByDao() {
        val categoryEntity = CategoryEntity(
            categoryId = 1,
            categoryName = "Test",
            collectionType = "Anime",
        )

        sut.insertCategoryEntity(categoryEntity)

        Mockito.verify(categoryDao).insertCategoryEntity(categoryEntity)
    }

    @Test
    fun testGetCategoriesByDao() {
        val collectionType = "Anime"

        sut.getCategories(collectionType)

        Mockito.verify(categoryDao).getCategories(collectionType)
    }

    @Test
    fun testClearCategoriesByCollectionTypeByDao() {
        val collectionType = "Anime"

        sut.clearCategoriesByCollectionType(collectionType)

        Mockito.verify(categoryDao).clearCategoriesByCollectionType(collectionType)
    }
}
