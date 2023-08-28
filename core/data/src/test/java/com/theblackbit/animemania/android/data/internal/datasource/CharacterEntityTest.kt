package com.theblackbit.animemania.android.data.internal.datasource

import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CharacterEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.toCharacterModel
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterEntityTest {

    @Test
    fun testConvertCharacterEntityToCharacterModel() {
        val entity = CharacterEntity(
            characterId = "1",
            name = "TestName",
            imageUrl = "https://imagetest",
            collectionId = "2",
            overView = "testoverview",
            page = 1
        )

        val result = entity.toCharacterModel()

        assertEquals(result.id, entity.characterId)
        assertEquals(result.name, entity.name)
        assertEquals(result.imageUrl, entity.imageUrl)
        assertEquals(result.overView, entity.overView)
    }
}
