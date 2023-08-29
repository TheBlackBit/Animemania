package com.theblackbit.animemania.android.model

import org.junit.Test
import kotlin.test.assertEquals

class ChapterTest {

    @Test
    fun `Chapter should have correct properties`() {
        val chapter = Chapter(
            id = "1",
            title = "Sample Chapter",
            synopsis = "This is a sample synopsis.",
            number = "1",
            seasonNumber = "1",
            imageUrl = "https://example.com/image.jpg"
        )

        assertEquals("1", chapter.id)
        assertEquals("Sample Chapter", chapter.title)
        assertEquals("This is a sample synopsis.", chapter.synopsis)
        assertEquals("1", chapter.number)
        assertEquals("1", chapter.seasonNumber)
        assertEquals("https://example.com/image.jpg", chapter.imageUrl)
    }
}
