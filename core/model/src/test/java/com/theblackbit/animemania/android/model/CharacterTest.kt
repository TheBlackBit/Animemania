package com.theblackbit.animemania.android.model

import org.junit.Test
import kotlin.test.assertEquals

class CharacterTest {

    @Test
    fun `Character should have correct properties`() {
        val character = Character(
            id = "1",
            name = "John Doe",
            imageUrl = "https://example.com/john.jpg",
            overView = "This is a sample overview."
        )

        assertEquals("1", character.id)
        assertEquals("John Doe", character.name)
        assertEquals("https://example.com/john.jpg", character.imageUrl)
        assertEquals("This is a sample overview.", character.overView)
    }
}
