package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.dao.CharacterDao
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CharacterEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterRoomRepositoryTest {

    @Mock
    lateinit var characterDao: CharacterDao

    private lateinit var sut: CharacterRoomRepository

    private val collectionId = "1"

    private val page = 1

    @Before
    fun setUp() {
        sut = CharacterRoomRepository(characterDao)
    }

    @Test
    fun testInsertCharacterByDao() {
        val characters = emptyList<CharacterEntity>()

        sut.insertCharacters(characters)

        Mockito.verify(characterDao).insertCharacters(characters)
    }

    @Test
    fun testGetCharactersByCollectionIdByDao() {
        sut.getCharactersByCollectionId(collectionId = collectionId, page = page)

        Mockito.verify(characterDao).getCharactersByCollectionId(
            collectionId = collectionId,
            page = page,
        )
    }

    @Test
    fun testDeleteCharactersByCollectionIdByDao() {
        sut.deleteCharactersByCollectionId(collectionId)

        Mockito.verify(characterDao).deleteCharactersByCollectionId(collectionId)
    }
}
