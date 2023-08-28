package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuAnimeDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
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
class AnimeByKitsuRepositoryTest {

    private lateinit var animeByKitsuRepository: AnimeByKitsuRemoteRepository

    @Mock
    private lateinit var safeApiRequest: SafeApiRequest

    @Mock
    lateinit var kitsuCollectionDataSource: KitsuAnimeDataSource

    private val pageLimit = "10"
    private val pageOffset = "0"
    private val expectedResponse = CollectionResponse(emptyList())

    @Before
    fun setup() {
        animeByKitsuRepository =
            AnimeByKitsuRemoteRepository(kitsuCollectionDataSource, safeApiRequest)
    }

    @Test
    fun testCollectTrendingAnimeSuccess() {
        `when`(
            kitsuCollectionDataSource.getTrendingCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.collectTrending(pageLimit, pageOffset)
                .blockingGet() as SafeApiRequest.ApiResultHandle.Success

        verify(kitsuCollectionDataSource).getTrendingCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset
        )

        assertEquals(expectedResponse, actualResponse.value)
    }

    @Test
    fun testCollectTrendingAnimeApiError() {
        `when`(
            kitsuCollectionDataSource.getTrendingCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        `when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getTrendingCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.just(SafeApiRequest.ApiResultHandle.ApiError))

        val actualResponse =
            animeByKitsuRepository.collectTrending(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testCollectTrendingAnimeHttpException() {
        `when`(
            kitsuCollectionDataSource.getTrendingCollection(
                pageLimit = pageLimit,
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
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getTrendingCollection(
                    pageLimit = pageLimit,
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
            animeByKitsuRepository.collectTrending(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testCollectTrendingAnimeIoException() {
        `when`(
            kitsuCollectionDataSource.getTrendingCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.error(IOException()))

        `when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getTrendingCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.error(IOException()))

        val actualResponse =
            animeByKitsuRepository.collectTrending(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.NetworkError)
    }

    @Test
    fun testGetMostAnticipatedAnime() {
        `when`(
            kitsuCollectionDataSource.getMostWantedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.getMostAnticipated(pageLimit, pageOffset)
                .blockingGet() as SafeApiRequest.ApiResultHandle.Success

        verify(kitsuCollectionDataSource).getMostWantedCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset
        )

        assertEquals(expectedResponse, actualResponse.value)
    }

    @Test
    fun testCollectMostAnticipatedAnimeApiError() {
        `when`(
            kitsuCollectionDataSource.getMostWantedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        `when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getMostWantedCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.just(SafeApiRequest.ApiResultHandle.ApiError))

        val actualResponse =
            animeByKitsuRepository.getMostAnticipated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testCollectMostAnticipatedAnimeHttpException() {
        `when`(
            kitsuCollectionDataSource.getMostWantedCollection(
                pageLimit = pageLimit,
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
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getMostWantedCollection(
                    pageLimit = pageLimit,
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
            animeByKitsuRepository.getMostAnticipated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testCollectMostAnticipatedAnimeIoException() {
        `when`(
            kitsuCollectionDataSource.getMostWantedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.error(IOException()))

        `when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getMostWantedCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.error(IOException()))

        val actualResponse =
            animeByKitsuRepository.getMostAnticipated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.NetworkError)
    }

    @Test
    fun testGetTopRatedAnime() {
        `when`(
            kitsuCollectionDataSource.getTopRatedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.getTopRated(pageLimit, pageOffset)
                .blockingGet() as SafeApiRequest.ApiResultHandle.Success

        verify(kitsuCollectionDataSource).getTopRatedCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset
        )

        assertEquals(expectedResponse, actualResponse.value)
    }

    @Test
    fun testGetTopRatedApiError() {
        `when`(
            kitsuCollectionDataSource.getTopRatedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        `when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getTopRatedCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.just(SafeApiRequest.ApiResultHandle.ApiError))

        val actualResponse =
            animeByKitsuRepository.getTopRated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetTopRatedHttpException() {
        `when`(
            kitsuCollectionDataSource.getTopRatedCollection(
                pageLimit = pageLimit,
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
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getTopRatedCollection(
                    pageLimit = pageLimit,
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
            animeByKitsuRepository.getTopRated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetTopRatedIoException() {
        `when`(
            kitsuCollectionDataSource.getTopRatedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.error(IOException()))

        `when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getTopRatedCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.error(IOException()))

        val actualResponse =
            animeByKitsuRepository.getTopRated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.NetworkError)
    }

    @Test
    fun testGetPopularAnime() {
        `when`(
            kitsuCollectionDataSource.getPopularCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.getPopular(pageLimit, pageOffset).blockingGet()
                as SafeApiRequest.ApiResultHandle.Success

        verify(kitsuCollectionDataSource).getPopularCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset
        )

        assertEquals(expectedResponse, actualResponse.value)
    }

    @Test
    fun testGetPopularApiError() {
        `when`(
            kitsuCollectionDataSource.getPopularCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        `when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getPopularCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.just(SafeApiRequest.ApiResultHandle.ApiError))

        val actualResponse =
            animeByKitsuRepository.getPopular(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetPopularHttpException() {
        `when`(
            kitsuCollectionDataSource.getPopularCollection(
                pageLimit = pageLimit,
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
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getPopularCollection(
                    pageLimit = pageLimit,
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
            animeByKitsuRepository.getPopular(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetPopularIoException() {
        `when`(
            kitsuCollectionDataSource.getPopularCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.error(IOException()))

        `when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getPopularCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.error(IOException()))

        val actualResponse =
            animeByKitsuRepository.getPopular(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.NetworkError)
    }
}
