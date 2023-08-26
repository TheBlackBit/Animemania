package com.theblackbit.animemania.android.data.external.datasource.response.collection

import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.PosterImage
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.validImage
import org.junit.Assert.assertEquals
import org.junit.Test

class PosterImageTest {

    @Test
    fun `test validImage with large image`() {
        val posterImage = PosterImage(
            large = "large.jpg",
            original = "original.jpg",
            small = "small.jpg",
            tiny = "tiny.jpg",
            medium = "medium.jpg"
        )

        val result = posterImage.validImage()

        assertEquals("large.jpg", result)
    }

    @Test
    fun `test validImage with medium image`() {
        val posterImage = PosterImage(
            original = "original.jpg",
            small = "small.jpg",
            tiny = "tiny.jpg",
            medium = "medium.jpg",
            large = null
        )

        val result = posterImage.validImage()

        assertEquals("medium.jpg", result)
    }

    @Test
    fun `test validImage with original image`() {
        val posterImage = PosterImage(
            small = "small.jpg",
            tiny = "tiny.jpg",
            original = "original.jpg",
            medium = null,
            large = null
        )

        val result = posterImage.validImage()

        assertEquals("original.jpg", result)
    }

    @Test
    fun `test validImage with small image`() {
        val posterImage = PosterImage(
            tiny = "tiny.jpg",
            small = "small.jpg",
            original = null,
            medium = null,
            large = null
        )

        val result = posterImage.validImage()

        assertEquals("small.jpg", result)
    }

    @Test
    fun `test validImage with tiny image`() {
        val posterImage = PosterImage(
            tiny = "tiny.jpg",
            small = null,
            original = null,
            medium = null,
            large = null
        )

        val result = posterImage.validImage()

        assertEquals("tiny.jpg", result)
    }

    @Test
    fun `test validImage with no images`() {
        val posterImage = PosterImage(
            small = null,
            tiny = null,
            original = null,
            medium = null,
            large = null
        )

        val result = posterImage.validImage()

        assertEquals("", result)
    }
}
