package com.theblackbit.animemania.android.data.external.datasource.response.collection

import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.Attributes
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.Data
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.PosterImage
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.toCollectionEntity
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.validImage
import org.junit.Assert.assertEquals
import org.junit.Test

class DataTest {

    @Test
    fun testDataToCollectionEntity() {
        val data = Data(
            id = "1",
            attributes = Attributes(
                averageRating = "8.5",
                canonicalTitle = "My Fake Anime",
                posterImage = PosterImage(
                    original = "original",
                    medium = "medium",
                    large = "large",
                    small = "small",
                    tiny = "tiny"
                ),
                endDate = "2023-12-31",
                coverImage = PosterImage(
                    original = "original",
                    medium = "medium",
                    large = "large",
                    small = "small",
                    tiny = "tiny"
                ),
                startDate = "2023-01-01",
                status = "Ongoing",
                synopsis = "This is a fake anime about...",
                userCount = 1000,
                youtubeVideoId = "abc123xyz"
            )
        )

        val collectionEntity = data.toCollectionEntity(
            collectionType = "Anime",
            typeOfRequest = RequestType.TRENDING_ANIME,
            page = 1
        )

        assertEquals(data.id, collectionEntity.collectionId)
        assertEquals(data.attributes!!.canonicalTitle, collectionEntity.name)
        assertEquals(data.attributes!!.averageRating + "%", collectionEntity.averageRating)
        assertEquals(data.attributes!!.startDate, collectionEntity.startDate)
        assertEquals(data.attributes!!.endDate, collectionEntity.endDate)
        assertEquals("", collectionEntity.genres)
        assertEquals("Anime", collectionEntity.collectionType)
        assertEquals("", collectionEntity.episodeCount)
        assertEquals(
            data.attributes!!.posterImage!!.validImage(),
            collectionEntity.miniPosterImageUrl
        )
        assertEquals(
            data.attributes!!.posterImage!!.validImage(),
            collectionEntity.bigPosterImageUrl
        )
        assertEquals(data.attributes!!.status, collectionEntity.status)
        assertEquals(data.attributes!!.synopsis, collectionEntity.synopsis)
        assertEquals(RequestType.TRENDING_ANIME.name, collectionEntity.typeOfRequest)
        assertEquals(1, collectionEntity.page)
    }

    @Test
    fun testDataToDataEntityNullData() {
        val page = 1
        val data = Data(
            id = null,
            attributes = null
        )
        val collectionEntity = data.toCollectionEntity(
            collectionType = "Anime",
            typeOfRequest = RequestType.TRENDING_ANIME,
            page = page
        )

        assertEquals("-1", collectionEntity.collectionId)
        assertEquals("", collectionEntity.name)
        assertEquals("", collectionEntity.averageRating)
        assertEquals("", collectionEntity.startDate)
        assertEquals("", collectionEntity.endDate)
        assertEquals("", collectionEntity.genres)
        assertEquals("Anime", collectionEntity.collectionType)
        assertEquals("", collectionEntity.episodeCount)
        assertEquals("", collectionEntity.miniPosterImageUrl)
        assertEquals("", collectionEntity.bigPosterImageUrl)
        assertEquals("", collectionEntity.status)
        assertEquals("", collectionEntity.synopsis)
        assertEquals(RequestType.TRENDING_ANIME.name, collectionEntity.typeOfRequest)
        assertEquals(page, collectionEntity.page)
    }

    @Test
    fun testDataToDataEntityNullAttributesData() {
        val page = 1
        val data = Data(
            id = "1",
            attributes = Attributes(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        )
        val collectionEntity = data.toCollectionEntity(
            collectionType = "Anime",
            typeOfRequest = RequestType.TRENDING_ANIME,
            page = page
        )

        assertEquals(data.id, collectionEntity.collectionId)
        assertEquals("", collectionEntity.name)
        assertEquals("", collectionEntity.averageRating)
        assertEquals("", collectionEntity.startDate)
        assertEquals("", collectionEntity.endDate)
        assertEquals("", collectionEntity.genres)
        assertEquals("Anime", collectionEntity.collectionType)
        assertEquals("", collectionEntity.episodeCount)
        assertEquals("", collectionEntity.miniPosterImageUrl)
        assertEquals("", collectionEntity.bigPosterImageUrl)
        assertEquals("", collectionEntity.status)
        assertEquals("", collectionEntity.synopsis)
        assertEquals(RequestType.TRENDING_ANIME.name, collectionEntity.typeOfRequest)
        assertEquals(1, collectionEntity.page)
    }
}
