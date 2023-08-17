package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuCharacterDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterData
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersByKitsuRepositoryImplTest {

    private lateinit var charactersByKitsuRepository: CharactersByKitsuRepositoryImpl

    @Mock
    lateinit var safeApiRequest: SafeApiRequest

    @Mock
    lateinit var kitsuCharacterDataSource: KitsuCharacterDataSource

    @Before
    fun setup() {
        charactersByKitsuRepository =
            CharactersByKitsuRepositoryImpl(kitsuCharacterDataSource, safeApiRequest)
    }

    @Test
    fun testGetCollectionCharacters() {
        val mediaType = "anime"
        val collectionId = "12345"
        val pageNumber = "1"
        val pageOffset = "0"
        val expectedCharacterData = CharacterResponse(
            listOf(
                CharacterData(
                    attributes = null,
                    id = null,
                    type = null
                )
            )
        )

        `when`(
            kitsuCharacterDataSource.getCollectionCharacters(
                mediaType = mediaType,
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            ),
        ).thenReturn(Single.just(expectedCharacterData))

        val actualCharacterData = charactersByKitsuRepository.getCollectionCharacters(
            mediaType = mediaType,
            collectionId = collectionId,
            pageNumber = pageNumber,
            pageOffset = pageOffset
        ).blockingGet() as SafeApiRequest.ApiResultHandle.Success

        verify(kitsuCharacterDataSource).getCollectionCharacters(
            mediaType = mediaType,
            collectionId = collectionId,
            pageNumber = pageNumber,
            pageOffset = pageOffset
        )

        assertEquals(expectedCharacterData, actualCharacterData.value)
    }
}
