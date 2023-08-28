package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuEpisodesDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodesResponse
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
class AnimeEpisodesByKitsuRepositoryTest {

    @Mock
    lateinit var kitsuEpisodesDataSource: KitsuEpisodesDataSource

    @Mock
    lateinit var safeApiRequest: SafeApiRequest

    private lateinit var animeEpisodesByKitsuRepository: AnimeEpisodesByKitsuRepository

    @Before
    fun setup() {
        animeEpisodesByKitsuRepository =
            AnimeEpisodesByKitsuRepository(kitsuEpisodesDataSource, safeApiRequest)
    }

    private val collectionId = "12345"
    private val pageNumber = "1"
    private val pageOffset = "0"
    private val expectedEpisodeData = EpisodesResponse(episodeData = emptyList())

    @Test
    fun testGetCollectionEpisodes() {
        `when`(
            kitsuEpisodesDataSource.getEpisodes(
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedEpisodeData))

        val actualEpisodesData =
            animeEpisodesByKitsuRepository.getCollectionEpisodes(
                collectionId,
                pageNumber,
                pageOffset
            )
                .blockingGet() as SafeApiRequest.ApiResultHandle.Success

        verify(kitsuEpisodesDataSource).getEpisodes(
            collectionId = collectionId,
            pageNumber = pageNumber,
            pageOffset = pageOffset
        )

        assertEquals(expectedEpisodeData, actualEpisodesData.value)
    }

    @Test
    fun testGetCollectionEpisodesApiError() {
        `when`(
            kitsuEpisodesDataSource.getEpisodes(
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedEpisodeData))

        `when`(
            safeApiRequest.request<EpisodesResponse> {
                animeEpisodesByKitsuRepository.getCollectionEpisodes(
                    collectionId,
                    pageNumber,
                    pageOffset
                ).map { it }
            }
        ).thenReturn(Single.just(SafeApiRequest.ApiResultHandle.ApiError))

        val actualResponse =
            animeEpisodesByKitsuRepository.getCollectionEpisodes(
                collectionId,
                pageNumber,
                pageOffset
            )
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetCollectionEpisodesHttpException() {
        `when`(
            kitsuEpisodesDataSource.getEpisodes(
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
            safeApiRequest.request<EpisodesResponse> {
                kitsuEpisodesDataSource.getEpisodes(
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
            animeEpisodesByKitsuRepository.getCollectionEpisodes(
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetCollectionEpisodesIoException() {
        `when`(
            kitsuEpisodesDataSource.getEpisodes(
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.error(IOException()))

        `when`(
            safeApiRequest.request<EpisodesResponse> {
                animeEpisodesByKitsuRepository.getCollectionEpisodes(
                    collectionId = collectionId,
                    pageNumber = pageNumber,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.error(IOException()))

        val actualResponse =
            animeEpisodesByKitsuRepository.getCollectionEpisodes(
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset
            )
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.NetworkError)
    }
}
