package com.theblackbit.animemania.android.data.external.datasource.response.episoderesponse

import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.ThumbnailEpisode
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.validImage
import org.junit.Assert
import org.junit.Test

class ThumbnailEpisodeTest {

    @Test
    fun `test validImage() with all image sizes`() {
        val thumbnail = ThumbnailEpisode(
            tiny = "tiny.jpg",
            large = "large.jpg",
            small = "small.jpg",
            medium = "medium.jpg",
            original = "original.jpg"
        )
        val result = thumbnail.validImage()
        Assert.assertEquals("original.jpg", result)
    }

    @Test
    fun `test validImage() with original size missing`() {
        val thumbnail = ThumbnailEpisode(
            tiny = "tiny.jpg",
            medium = "medium.jpg",
            original = null,
            large = "large.jpg",
            small = "small.jpg"
        )
        val result = thumbnail.validImage()
        Assert.assertEquals("medium.jpg", result)
    }

    @Test
    fun `test validImage() with original and medium size missing`() {
        val thumbnail = ThumbnailEpisode(
            tiny = "tiny.jpg",
            medium = null,
            original = null,
            large = "large.jpg",
            small = "small.jpg"
        )
        val result = thumbnail.validImage()
        Assert.assertEquals("large.jpg", result)
    }

    @Test
    fun `test validImage() with original, medium and large size missing`() {
        val thumbnail = ThumbnailEpisode(
            tiny = "tiny.jpg",
            medium = null,
            original = null,
            large = null,
            small = "small.jpg"
        )
        val result = thumbnail.validImage()
        Assert.assertEquals("small.jpg", result)
    }

    @Test
    fun `test validImage() with original, medium, large small size missing`() {
        val thumbnail = ThumbnailEpisode(
            tiny = "tiny.jpg",
            medium = null,
            original = null,
            large = null,
            small = null
        )
        val result = thumbnail.validImage()
        Assert.assertEquals("tiny.jpg", result)
    }

    @Test
    fun `test validImage() with no image sizes available`() {
        val thumbnail = ThumbnailEpisode(
            tiny = null,
            large = null,
            small = null,
            medium = null,
            original = null
        )
        val result = thumbnail.validImage()
        Assert.assertEquals("", result)
    }
}
