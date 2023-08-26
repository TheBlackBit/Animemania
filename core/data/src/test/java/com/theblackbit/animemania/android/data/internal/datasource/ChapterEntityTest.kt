package com.theblackbit.animemania.android.data.internal.datasource

import com.theblackbit.animemania.android.data.internal.datasource.room.entity.ChapterEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.toChapterModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ChapterEntityTest {

    @Test
    fun testConvertChapterEntityToChapterModel() {
        val chapterEntity = ChapterEntity(
            chapterId = "1",
            title = "test",
            synopsis = "Test",
            collectionId = "1",
            number = 1,
            seasonNumber = 1,
            imageUrl = "https://test.com",
            pageNumber = 1
        )

        val result = chapterEntity.toChapterModel()

        assertEquals(result.id, chapterEntity.chapterId)
        assertEquals(result.title, chapterEntity.title)
        assertEquals(result.synopsis, chapterEntity.synopsis)
        assertEquals(result.imageUrl, chapterEntity.imageUrl)
        assertEquals(result.number, chapterEntity.number.toString())
        assertEquals(result.seasonNumber, chapterEntity.seasonNumber.toString())
    }
}
