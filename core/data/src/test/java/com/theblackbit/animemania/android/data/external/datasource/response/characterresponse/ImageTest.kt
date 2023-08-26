package com.theblackbit.animemania.android.data.external.datasource.response.characterresponse

import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.Image
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.validImage
import org.junit.Assert
import org.junit.Test

class ImageTest {

    @Test
    fun `test validImage() with all image sizes`() {
        val image = Image(
            tiny = "tiny.jpg",
            large = "large.jpg",
            small = "small.jpg",
            medium = "medium.jpg",
            original = "original.jpg"
        )
        val result = image.validImage()
        Assert.assertEquals("original.jpg", result)
    }

    @Test
    fun `test validImage() with original size missing`() {
        val image = Image(
            tiny = "tiny.jpg",
            medium = "medium.jpg",
            original = null,
            large = "large.jpg",
            small = "small.jpg"
        )
        val result = image.validImage()
        Assert.assertEquals("medium.jpg", result)
    }

    @Test
    fun `test validImage() with original and medium size missing`() {
        val image = Image(
            tiny = "tiny.jpg",
            medium = null,
            original = null,
            large = "large.jpg",
            small = "small.jpg"
        )
        val result = image.validImage()
        Assert.assertEquals("large.jpg", result)
    }

    @Test
    fun `test validImage() with original, medium and large size missing`() {
        val image = Image(
            tiny = "tiny.jpg",
            medium = null,
            original = null,
            large = null,
            small = "small.jpg"
        )
        val result = image.validImage()
        Assert.assertEquals("small.jpg", result)
    }

    @Test
    fun `test validImage() with original, medium, large small size missing`() {
        val image = Image(
            tiny = "tiny.jpg",
            medium = null,
            original = null,
            large = null,
            small = null
        )
        val result = image.validImage()
        Assert.assertEquals("tiny.jpg", result)
    }

    @Test
    fun `test validImage() with no image sizes available`() {
        val image = Image(
            tiny = null,
            large = null,
            small = null,
            medium = null,
            original = null
        )
        val result = image.validImage()
        Assert.assertEquals(null, result)
    }
}
