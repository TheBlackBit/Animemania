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
            type = "Anime",
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
                ),
                createdAt = "test",
                length = 1,
                updatedAt = "test"
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
            type = null,
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
            type = "test",
            attributes = ChapterAttributes(
                canonicalTitle = null,
                synopsis = null,
                number = null,
                volumeNumber = null,
                thumbnail = null,
                createdAt = null,
                length = null,
                updatedAt = null
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
    fun testChapterDataToChapterEntityImageUrlOriginalIsNull() {
        val page = 1
        val collectionId = "1"
        val chapterData = ChapterData(
            id = "1",
            type = "Anime",
            attributes = ChapterAttributes(
                canonicalTitle = "Chapter 1",
                synopsis = "Synopsis for Chapter 1",
                number = 1,
                volumeNumber = 1,
                thumbnail = ThumbnailChapter(
                    tiny = "tiny",
                    large = "large",
                    small = "small",
                    medium = "medium",
                    original = null
                ),
                createdAt = "test",
                length = 1,
                updatedAt = "test"
            )
        )

        val chapterEntity = chapterData.toChapterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(chapterEntity.imageUrl, chapterData.attributes?.thumbnail?.medium)
    }

    @Test
    fun testChapterDataToChapterEntityImageUrlOriginalAndMediumAreNull() {
        val page = 1
        val collectionId = "1"
        val chapterData = ChapterData(
            id = "1",
            type = "Anime",
            attributes = ChapterAttributes(
                canonicalTitle = "Chapter 1",
                synopsis = "Synopsis for Chapter 1",
                number = 1,
                volumeNumber = 1,
                thumbnail = ThumbnailChapter(
                    tiny = "tiny",
                    large = "large",
                    small = "small",
                    medium = null,
                    original = null
                ),
                createdAt = "test",
                length = 1,
                updatedAt = "test"
            )
        )

        val chapterEntity = chapterData.toChapterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(chapterEntity.imageUrl, chapterData.attributes?.thumbnail?.large)
    }

    @Test
    fun testChapterDataToChapterEntityImageUrlOriginalMediumAndLargeAreNull() {
        val page = 1
        val collectionId = "1"
        val chapterData = ChapterData(
            id = "1",
            type = "Anime",
            attributes = ChapterAttributes(
                canonicalTitle = "Chapter 1",
                synopsis = "Synopsis for Chapter 1",
                number = 1,
                volumeNumber = 1,
                thumbnail = ThumbnailChapter(
                    tiny = "tiny",
                    large = null,
                    small = "small",
                    medium = null,
                    original = null
                ),
                createdAt = "test",
                length = 1,
                updatedAt = "test"
            )
        )

        val chapterEntity = chapterData.toChapterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(chapterEntity.imageUrl, chapterData.attributes?.thumbnail?.small)
    }

    @Test
    fun testChapterDataToChapterEntityImageUrlOriginalMediumLargeAndSmallAreNull() {
        val page = 1
        val collectionId = "1"
        val chapterData = ChapterData(
            id = "1",
            type = "Anime",
            attributes = ChapterAttributes(
                canonicalTitle = "Chapter 1",
                synopsis = "Synopsis for Chapter 1",
                number = 1,
                volumeNumber = 1,
                thumbnail = ThumbnailChapter(
                    tiny = "tiny",
                    large = null,
                    small = null,
                    medium = null,
                    original = null
                ),
                createdAt = "test",
                length = 1,
                updatedAt = "test"
            )
        )

        val chapterEntity = chapterData.toChapterEntity(page = page, collectionId = collectionId)

        Assert.assertEquals(chapterEntity.imageUrl, chapterData.attributes?.thumbnail?.tiny)
    }
}
