package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuCharacterDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterData
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.io.IOException

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

    private val mediaType = "anime"
    private val collectionId = "12345"
    private val pageNumber = "1"
    private val pageOffset = "0"
    private val expectedCharacterData = CharacterResponse(
        listOf(
            CharacterData(
                attributes = null,
                id = null
            )
        )
    )

    @Test
    fun testGetCollectionCharacters() {
        `when`(
            kitsuCharacterDataSource.getCollectionCharacters(
                mediaType = mediaType,
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
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

    @Test
    fun testGetCollectionCharactersApiError() {
        `when`(
            kitsuCharacterDataSource.getCollectionCharacters(
                mediaType = mediaType,
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedCharacterData))

        `when`(
            safeApiRequest.request<CharacterResponse> {
                kitsuCharacterDataSource.getCollectionCharacters(
                    mediaType = mediaType,
                    collectionId = collectionId,
                    pageNumber = pageNumber,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.just(SafeApiRequest.ApiResultHandle.ApiError))

        val actualResponse =
            charactersByKitsuRepository.getCollectionCharacters(
                mediaType = mediaType,
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetCollectionCharactersHttpException() {
        `when`(
            kitsuCharacterDataSource.getCollectionCharacters(
                mediaType = mediaType,
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
        ).thenReturn(
            Single.error(
                retrofit2.HttpException(
                    Response.error<Any>(
                        400,
                        "".toResponseBody(null)
                    )
                )
            )
        )

        `when`(
            safeApiRequest.request<CharacterResponse> {
                kitsuCharacterDataSource.getCollectionCharacters(
                    mediaType = mediaType,
                    collectionId = collectionId,
                    pageNumber = pageNumber,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(
            Single.error(
                retrofit2.HttpException(
                    Response.error<Any>(
                        400,
                        "".toResponseBody(null)
                    )
                )
            )
        )

        val actualResponse =
            charactersByKitsuRepository.getCollectionCharacters(
                mediaType = mediaType,
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetCollectionCharactersIoException() {
        `when`(
            kitsuCharacterDataSource.getCollectionCharacters(
                mediaType = mediaType,
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.error(IOException()))

        `when`(
            safeApiRequest.request<CharacterResponse> {
                kitsuCharacterDataSource.getCollectionCharacters(
                    mediaType = mediaType,
                    collectionId = collectionId,
                    pageNumber = pageNumber,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.error(IOException()))

        val actualResponse =
            charactersByKitsuRepository.getCollectionCharacters(
                mediaType = mediaType,
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.NetworkError)
    }
}
