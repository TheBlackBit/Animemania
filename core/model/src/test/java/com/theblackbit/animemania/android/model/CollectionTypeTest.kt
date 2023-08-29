package com.theblackbit.animemania.android.model

import org.junit.Test
import kotlin.test.assertEquals

class CollectionTypeTest {

    @Test
    fun `test collection type get name should be anime`() {
        val collectionType = CollectionType.ANIME

        assertEquals(collectionType.collectionName, "Anime")
    }

    @Test
    fun `test collection type get name should be manga`() {
        val collectionType = CollectionType.MANGA

        assertEquals(collectionType.collectionName, "Manga")
    }
}
