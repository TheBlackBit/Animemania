package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.theblackbit.animemania.android.data.internal.datasource.room.AnimeManiaRoom
import com.theblackbit.animemania.android.data.internal.datasource.room.dao.data.categoryEntities
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestCategoriesDao {
    private lateinit var db: AnimeManiaRoom

    private lateinit var categoryDao: CategoryDao

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AnimeManiaRoom::class.java)
            .build()
        categoryDao = db.categoryDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertAndCollectCategories() {
        categoryEntities.forEach { categoryDao.insertCategoryEntity(it) }

        categoryDao.getCategories("Anime")
            .test()
            .assertValue {
                it.isNotEmpty() &&
                    it.all { categoryEntity -> categoryEntity.collectionType == "Anime" }
            }
    }

    @Test
    fun testClearCategoriesByCollectionType() {
        categoryEntities.forEach { categoryDao.insertCategoryEntity(it) }

        categoryDao.clearCategoriesByCollectionType("Anime")

        categoryDao.getCategories("Anime")
            .test()
            .assertValue {
                it.isEmpty()
            }

        categoryDao.getCategories("Manga")
            .test()
            .assertValue {
                it.isNotEmpty()
            }
    }
}
