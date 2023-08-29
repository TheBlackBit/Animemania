package com.theblackbit.animemania.android.model

import org.junit.Test
import kotlin.test.assertEquals

class CollectionTest {

    @Test
    fun `Collection should have correct properties`() {
        val genres = listOf(Genre("Action"), Genre("Adventure"))
        val collection = Collection(
            collectionId = "1",
            name = "Sample Collection",
            averageRating = "4.5",
            startDate = "2023-01-01",
            endDate = "2023-12-31",
            genre = genres,
            collectionType = CollectionType.ANIME,
            episodeCount = "10",
            miniPosterImageUrl = "https://example.com/mini-poster.jpg",
            bigPosterImageUrl = "https://example.com/big-poster.jpg",
            status = "Ongoing",
            synopsis = "This is a sample collection synopsis."
        )

        assertEquals("1", collection.collectionId)
        assertEquals("Sample Collection", collection.name)
        assertEquals("4.5", collection.averageRating)
        assertEquals("2023-01-01", collection.startDate)
        assertEquals("2023-12-31", collection.endDate)
        assertEquals(genres, collection.genre)
        assertEquals(CollectionType.ANIME, collection.collectionType)
        assertEquals("10", collection.episodeCount)
        assertEquals("https://example.com/mini-poster.jpg", collection.miniPosterImageUrl)
        assertEquals("https://example.com/big-poster.jpg", collection.bigPosterImageUrl)
        assertEquals("Ongoing", collection.status)
        assertEquals("This is a sample collection synopsis.", collection.synopsis)
    }
}
