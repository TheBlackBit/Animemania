package com.theblackbit.animemania.android.model

import org.junit.Test
import kotlin.test.assertEquals

class GenreTest {

    @Test
    fun `Genre should have correct name`() {
        val genre = Genre("Action")
        assertEquals("Action", genre.name)
    }
}
