package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theblackbit.animemania.android.data.internal.datasource.room.AnimeManiaRoom
import com.theblackbit.animemania.android.data.internal.datasource.room.dao.data.categoryEntities
import com.theblackbit.animemania.android.data.internal.datasource.room.dao.data.collectionList
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionCategoryJoinEntity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestCollectionsDao {

    private lateinit var db: AnimeManiaRoom

    private lateinit var categoryDao: CategoryDao

    private lateinit var collectionDao: CollectionDao

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AnimeManiaRoom::class.java)
            .build()
        categoryDao = db.categoryDao()
        collectionDao = db.collectionDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertAndCollectEntitiesByPageAndCategory() {
        val pageNumber = 1
        val categoryId = categoryEntities[0].categoryId
        val collectionType = categoryEntities[0].collectionType
        val listCategoryJoinEntity: List<CollectionCategoryJoinEntity> = collectionList
            .map { collectionEntity ->
                CollectionCategoryJoinEntity(
                    collectionId = collectionEntity.collectionId,
                    categoryId = categoryId,
                    pageNumber = pageNumber,
                )
            }

        categoryEntities.forEach { categoryDao.insertCategoryEntity(it) }

        collectionDao.insertCollectionEntities(collectionEntities = collectionList)

        collectionDao.insertCollectionCategoryJoinEntities(listCategoryJoinEntity)

        collectionDao.collectPagedCollectionsByCategory(
            pageNumber = pageNumber,
            categoryId = categoryId,
        )
            .test()
            .assertValue { collectionCategory ->
                collectionCategory.isNotEmpty() &&
                    collectionCategory.all { collectionJoin ->
                        collectionJoin.collectionCategoryJoinEntity.pageNumber == pageNumber &&
                            collectionJoin.collectionCategoryJoinEntity.categoryId == categoryId &&
                            collectionJoin.collections.all { collection ->
                                collection.collectionType == collectionType
                            }
                    }
            }
    }

    @Test
    fun testDeleteCollectionsByCategory() {
        val pageNumber = 1
        val categoryId = categoryEntities[0].categoryId
        val listCategoryJoinEntity: List<CollectionCategoryJoinEntity> = collectionList
            .map { collectionEntity ->
                CollectionCategoryJoinEntity(
                    collectionId = collectionEntity.collectionId,
                    categoryId = categoryId,
                    pageNumber = pageNumber,
                )
            }

        categoryEntities.forEach { categoryDao.insertCategoryEntity(it) }

        collectionDao.insertCollectionEntities(collectionEntities = collectionList)

        collectionDao.insertCollectionCategoryJoinEntities(listCategoryJoinEntity)

        collectionDao.clearCollectioncategoryjoinentity(categoryId)

        collectionDao.clearCollectionEntitiesByCategory(categoryId)

        collectionDao.collectPagedCollectionsByCategory(
            pageNumber = pageNumber,
            categoryId = categoryId,
        )
            .test()
            .assertValue { collectionCategory ->
                collectionCategory.isEmpty()
            }
    }
}
