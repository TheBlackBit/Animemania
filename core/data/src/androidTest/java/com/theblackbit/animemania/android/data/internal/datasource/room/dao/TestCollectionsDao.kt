package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theblackbit.animemania.android.data.internal.datasource.room.AnimeManiaRoom
import com.theblackbit.animemania.android.data.internal.datasource.room.dao.data.collectionList
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestCollectionsDao {

    private lateinit var db: AnimeManiaRoom

    private lateinit var collectionDao: CollectionDao

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AnimeManiaRoom::class.java)
            .build()
        collectionDao = db.collectionDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertAndCollectEntitiesByPageAndCategory() {
        val pageNumber = 1

        collectionDao.insertCollectionEntities(collectionEntities = collectionList)

        collectionDao.collectPagedCollectionsByTypeOfRequest(
            pageNumber = pageNumber,
            typeOfRequest = "TRENDING_ANIME",
        )
            .test()
            .assertValue { collectionCategory ->
                collectionCategory.isNotEmpty() &&
                    collectionCategory.all { collection ->
                        collection.page == 1 &&
                            collection.typeOfRequest == "TRENDING_ANIME"
                    }
            }
    }

    @Test
    fun testDeleteCollectionsByCategory() {
        val pageNumber = 1

        collectionDao.insertCollectionEntities(collectionEntities = collectionList)

        collectionDao.clearCollectioncategoryjoinentity("TRENDING_ANIME")

        collectionDao.collectPagedCollectionsByTypeOfRequest(
            pageNumber = pageNumber,
            typeOfRequest = "TRENDING_ANIME",
        )
            .test()
            .assertValue {
                it.isEmpty()
            }
    }
}
