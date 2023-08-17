package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.dao.ChapterDao
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.ChapterEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChapterRoomRepositoryTest {

    @Mock
    lateinit var chapterDao: ChapterDao

    private lateinit var sut: ChapterRoomRepository

    private val collectionId = "1"

    @Before
    fun setUp() {
        sut = ChapterRoomRepository(chapterDao)
    }

    @Test
    fun testInsertChapterByDao() {
        val listOfChapters = emptyList<ChapterEntity>()
        sut.insertChapters(listOfChapters)

        Mockito.verify(chapterDao).insertChapters(listOfChapters)
    }

    @Test
    fun testGetChaptersByCollectionByDao() {
        sut.getChaptersByCollection(collectionId = collectionId, pageNumber = 1)

        Mockito.verify(chapterDao).getChaptersByCollection(collectionId, 1)
    }

    @Test
    fun testDeleteChaptersByCollectionIdByDao() {
        sut.deleteChaptersByCollection(collectionId)

        Mockito.verify(chapterDao).deleteChaptersByCollection(collectionId)
    }
}
