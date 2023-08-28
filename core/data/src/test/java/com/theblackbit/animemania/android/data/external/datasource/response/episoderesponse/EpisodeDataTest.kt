package com.theblackbit.animemania.android.data.external.datasource.response.episoderesponse

import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodeAttributes
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodeData
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.ThumbnailEpisode
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.toChapterEntity
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.validImage
import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeDataTest {

    @Test
    fun testEpisodeDataToChapterEntity() {
        val episodeData = EpisodeData(
            attributes = EpisodeAttributes(
                canonicalTitle = "Test",
                number = 1,
                seasonNumber = 1,
                synopsis = "Test",
                thumbnail = ThumbnailEpisode("tiny", "large", "small", "medium", "original")
            ),
            id = "1"
        )

        val result = episodeData.toChapterEntity(page = 1, collectionId = "1")

        assertEquals(result.chapterId, episodeData.id)
        assertEquals(result.number, episodeData.attributes!!.number)
        assertEquals(result.title, episodeData.attributes!!.canonicalTitle)
        assertEquals(result.imageUrl, episodeData.attributes!!.thumbnail?.validImage())
        assertEquals(result.seasonNumber, episodeData.attributes!!.seasonNumber)
        assertEquals(result.synopsis, episodeData.attributes!!.synopsis)
        assertEquals(result.collectionId, 1.toString())
        assertEquals(result.pageNumber, 1)
    }

    @Test
    fun testEpisodeDataNullDataToChapterEntity() {
        val episodeData = EpisodeData(
            attributes = EpisodeAttributes(
                canonicalTitle = null,
                number = null,
                seasonNumber = null,
                synopsis = null,
                thumbnail = null
            ),
            id = null
        )

        val result = episodeData.toChapterEntity(page = 1, collectionId = "1")

        assertEquals(result.chapterId, "-1")
        assertEquals(result.number, -1)
        assertEquals(result.title, "")
        assertEquals(result.imageUrl, "")
        assertEquals(result.seasonNumber, -1)
        assertEquals(result.synopsis, "")
        assertEquals(result.collectionId, 1.toString())
        assertEquals(result.pageNumber, 1)
    }

    @Test
    fun testEpisodeDataAttributesNullToChapterEntity() {
        val episodeData = EpisodeData(
            attributes = null,
            id = null
        )

        val result = episodeData.toChapterEntity(page = 1, collectionId = "1")

        assertEquals(result.chapterId, "-1")
        assertEquals(result.number, -1)
        assertEquals(result.title, "")
        assertEquals(result.imageUrl, "")
        assertEquals(result.seasonNumber, -1)
        assertEquals(result.synopsis, "")
        assertEquals(result.collectionId, 1.toString())
        assertEquals(result.pageNumber, 1)
    }

    @Test
    fun testEpisodeDataToChapterEntityNullImages() {
        val episodeData = EpisodeData(
            attributes = EpisodeAttributes(
                canonicalTitle = null,
                number = null,
                seasonNumber = null,
                synopsis = null,
                thumbnail = ThumbnailEpisode(
                    null,
                    null,
                    null,
                    null,
                    null
                )
            ),
            id = null
        )

        val result = episodeData.toChapterEntity(page = 1, collectionId = "1")

        assertEquals(result.chapterId, "-1")
        assertEquals(result.number, -1)
        assertEquals(result.title, "")
        assertEquals(result.imageUrl, "")
        assertEquals(result.seasonNumber, -1)
        assertEquals(result.synopsis, "")
        assertEquals(result.collectionId, 1.toString())
        assertEquals(result.pageNumber, 1)
    }
}
