package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theblackbit.animemania.android.data.internal.datasource.room.AnimeManiaRoom
import com.theblackbit.animemania.android.data.internal.datasource.room.dao.data.chapterList
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// TODO: RUN TEST
@RunWith(AndroidJUnit4::class)
class TestChaptersDao {

    private lateinit var db: AnimeManiaRoom

    private lateinit var chapterDao: ChapterDao

    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AnimeManiaRoom::class.java)
            .build()
        chapterDao = db.chapterDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertAndCollectChapters() {
        val collectionId = 1
        chapterDao.insertChapters(chapterList)
        chapterDao.getChaptersByCollection(collectionId, 1)
            .test()
            .assertValue { chapterEntities ->
                chapterEntities.isNotEmpty() &&
                    chapterEntities.all {
                        it.collectionId == collectionId
                    }
            }
    }

    @Test
    fun testDeleteChaptersByCollectionId() {
        val collectionId = 1
        chapterDao.insertChapters(chapterList)
        chapterDao.deleteChaptersByCollection(collectionId)
        chapterDao.getChaptersByCollection(collectionId, 1)
            .test()
            .assertValue { chapterEntities ->
                chapterEntities.isEmpty()
            }
    }
}
