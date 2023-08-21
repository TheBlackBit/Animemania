package com.theblackbit.animemania.android.data.external.datasource.response.chapterresponse

import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ThumbnailChapter
import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.validImage
import org.junit.Assert.assertEquals
import org.junit.Test

class ThumbnailChapterTest {

    @Test
    fun `test validImage() with all image sizes`() {
        val thumbnail = ThumbnailChapter(
            tiny = "tiny.jpg",
            large = "large.jpg",
            small = "small.jpg",
            medium = "medium.jpg",
            original = "original.jpg"
        )
        val result = thumbnail.validImage()
        assertEquals("original.jpg", result)
    }

    @Test
    fun `test validImage() with original size missing`() {
        val thumbnail = ThumbnailChapter(
            tiny = "tiny.jpg",
            medium = "medium.jpg",
            original = null,
            large = "large.jpg",
            small = "small.jpg"
        )
        val result = thumbnail.validImage()
        assertEquals("medium.jpg", result)
    }

    @Test
    fun `test validImage() with original and medium size missing`() {
        val thumbnail = ThumbnailChapter(
            tiny = "tiny.jpg",
            medium = null,
            original = null,
            large = "large.jpg",
            small = "small.jpg"
        )
        val result = thumbnail.validImage()
        assertEquals("large.jpg", result)
    }

    @Test
    fun `test validImage() with original, medium and large size missing`() {
        val thumbnail = ThumbnailChapter(
            tiny = "tiny.jpg",
            medium = null,
            original = null,
            large = null,
            small = "small.jpg"
        )
        val result = thumbnail.validImage()
        assertEquals("small.jpg", result)
    }

    @Test
    fun `test validImage() with original, medium, large small size missing`() {
        val thumbnail = ThumbnailChapter(
            tiny = "tiny.jpg",
            medium = null,
            original = null,
            large = null,
            small = null
        )
        val result = thumbnail.validImage()
        assertEquals("tiny.jpg", result)
    }

    @Test
    fun `test validImage() with no image sizes available`() {
        val thumbnail = ThumbnailChapter(
            tiny = null,
            large = null,
            small = null,
            medium = null,
            original = null
        )
        val result = thumbnail.validImage()
        assertEquals("", result)
    }
}
