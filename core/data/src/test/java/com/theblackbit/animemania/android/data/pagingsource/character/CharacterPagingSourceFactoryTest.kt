package com.theblackbit.animemania.android.data.pagingsource.character

import com.theblackbit.animemania.android.data.external.repository.CharacterByKitsuRepository
import com.theblackbit.animemania.android.data.internal.repository.CharacterLocalRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterPagingSourceFactoryTest {

    private lateinit var sut: CharacterPagingSourceFactory

    @Mock
    private lateinit var localRepository: CharacterLocalRepository

    @Mock
    private lateinit var remoteRepository: CharacterByKitsuRepository

    @Before
    fun setUp() {
        sut = CharacterPagingSourceFactory(
            localRepository,
            remoteRepository
        )
    }

    @Test
    fun testCharactersPagingSource() {
        val result = sut.getCharacterPagingSourceByMediaType(
            collectionId = "1",
            mediaType = "Test"
        )

        assert(result is CharacterPagingSource)
    }
}
