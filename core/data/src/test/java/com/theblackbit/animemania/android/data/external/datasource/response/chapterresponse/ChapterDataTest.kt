package com.theblackbit.animemania.android.data.external.datasource.response.chapterresponse

import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ChapterAttributes
import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ChapterData
import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ThumbnailChapter
import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.toChapterEntity
import org.junit.Assert
import org.junit.Test

class ChapterDataTest {

    @Test
    fun testChapterDataToChapterEntity() {
        val page = 1
        val collectionId = "1"
        val chapterData = ChapterData(
            id = "1",
            attributes = ChapterAttributes(
                canonicalTitle = "Chapter 1",
                synopsis = "Synopsis for Chapter 1",
                number = 1,
                volumeNumber = 1,
                thumbnail = ThumbnailChapter(
                    tiny = "test",
                    large = "test",
                    small = "test",
                    medium = "test",
                    original = "test"
                )
            )
        )

        val chapterEntity = chapterData.toChapterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(chapterEntity.chapterId, chapterData.id)
        Assert.assertEquals(chapterEntity.title, chapterData.attributes?.canonicalTitle)
        Assert.assertEquals(chapterEntity.synopsis, chapterData.attributes?.synopsis)
        Assert.assertEquals(chapterEntity.collectionId, collectionId)
        Assert.assertEquals(chapterEntity.number, chapterData.attributes?.number)
        Assert.assertEquals(chapterEntity.seasonNumber, chapterData.attributes?.volumeNumber)
        Assert.assertEquals(chapterEntity.imageUrl, chapterData.attributes?.thumbnail?.original)
        Assert.assertEquals(chapterEntity.pageNumber, page)
    }

    @Test
    fun testChapterDataToChapterEntityNullData() {
        val page = 1
        val collectionId = "1"
        val chapterData = ChapterData(
            id = null,
            attributes = null
        )

        val chapterEntity = chapterData.toChapterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(chapterEntity.chapterId, "-1")
        Assert.assertEquals(chapterEntity.title, "")
        Assert.assertEquals(chapterEntity.synopsis, "")
        Assert.assertEquals(chapterEntity.collectionId, collectionId)
        Assert.assertEquals(chapterEntity.number, -1)
        Assert.assertEquals(chapterEntity.seasonNumber, -1)
        Assert.assertEquals(chapterEntity.imageUrl, "")
        Assert.assertEquals(chapterEntity.pageNumber, page)
    }

    @Test
    fun testChapterDataToChapterEntityAttributesNull() {
        val page = 1
        val collectionId = "1"
        val chapterData = ChapterData(
            id = "1",
            attributes = ChapterAttributes(
                canonicalTitle = null,
                synopsis = null,
                number = null,
                volumeNumber = null,
                thumbnail = null
            )
        )

        val chapterEntity = chapterData.toChapterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(chapterEntity.chapterId, chapterData.id)
        Assert.assertEquals(chapterEntity.title, "")
        Assert.assertEquals(chapterEntity.synopsis, "")
        Assert.assertEquals(chapterEntity.collectionId, collectionId)
        Assert.assertEquals(chapterEntity.number, -1)
        Assert.assertEquals(chapterEntity.seasonNumber, -1)
        Assert.assertEquals(chapterEntity.imageUrl, "")
        Assert.assertEquals(chapterEntity.pageNumber, page)
    }

    @Test
    fun testChapterDataToChapterEntityImageUrlIsNull() {
        val page = 1
        val collectionId = "1"
        val chapterData = ChapterData(
            id = "1",
            attributes = ChapterAttributes(
                canonicalTitle = "Chapter 1",
                synopsis = "Synopsis for Chapter 1",
                number = 1,
                volumeNumber = 1,
                thumbnail = ThumbnailChapter(
                    tiny = null,
                    large = null,
                    small = null,
                    medium = null,
                    original = null
                )
            )
        )

        val chapterEntity = chapterData.toChapterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(chapterEntity.imageUrl, "")
    }
}
