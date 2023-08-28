package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuMangaDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class MangaByKitsuRepositoryTest {

    private lateinit var mangaByKitsuRepository: MangaByKitsuRemoteRepository

    @Mock
    lateinit var kitsuCollectionDataSource: KitsuMangaDataSource

    @Mock
    lateinit var safeApiRequest: SafeApiRequest

    private val pageLimit = "10"
    private val pageOffset = "0"
    private val expectedResponse = CollectionResponse(emptyList())

    @Before
    fun setup() {
        mangaByKitsuRepository =
            MangaByKitsuRemoteRepository(kitsuCollectionDataSource, safeApiRequest)
    }

    @Test
    fun testCollectTrendingManga() {
        Mockito.`when`(
            kitsuCollectionDataSource.getTrendingCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            mangaByKitsuRepository.collectTrending(pageLimit, pageOffset)
                .blockingGet() as SafeApiRequest.ApiResultHandle.Success

        Mockito.verify(kitsuCollectionDataSource).getTrendingCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset
        )

        Assert.assertEquals(expectedResponse, actualResponse.value)
    }

    @Test
    fun testCollectTrendingMangaApiError() {
        Mockito.`when`(
            kitsuCollectionDataSource.getTrendingCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        Mockito.`when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getTrendingCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.just(SafeApiRequest.ApiResultHandle.ApiError))

        val actualResponse =
            mangaByKitsuRepository.collectTrending(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testCollectTrendingMangaHttpException() {
        Mockito.`when`(
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

        Mockito.`when`(
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
            mangaByKitsuRepository.collectTrending(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testCollectTrendingMangaIoException() {
        Mockito.`when`(
            kitsuCollectionDataSource.getTrendingCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.error(IOException()))

        Mockito.`when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getTrendingCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.error(IOException()))

        val actualResponse =
            mangaByKitsuRepository.collectTrending(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.NetworkError)
    }

    @Test
    fun testGetMostAnticipatedManga() {
        Mockito.`when`(
            kitsuCollectionDataSource.getMostWantedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            mangaByKitsuRepository.getMostAnticipated(pageLimit, pageOffset)
                .blockingGet() as SafeApiRequest.ApiResultHandle.Success

        Mockito.verify(kitsuCollectionDataSource).getMostWantedCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset
        )

        Assert.assertEquals(expectedResponse, actualResponse.value)
    }

    @Test
    fun testGetMostAnticipatedMangaApiError() {
        Mockito.`when`(
            kitsuCollectionDataSource.getMostWantedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        Mockito.`when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getMostWantedCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.just(SafeApiRequest.ApiResultHandle.ApiError))

        val actualResponse =
            mangaByKitsuRepository.getMostAnticipated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetMostAnticipatedMangaHttpException() {
        Mockito.`when`(
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

        Mockito.`when`(
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
            mangaByKitsuRepository.getMostAnticipated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetMostAnticipatedMangaIoException() {
        Mockito.`when`(
            kitsuCollectionDataSource.getMostWantedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.error(IOException()))

        Mockito.`when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getMostWantedCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.error(IOException()))

        val actualResponse =
            mangaByKitsuRepository.getMostAnticipated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.NetworkError)
    }

    @Test
    fun testGetTopRatedManga() {
        Mockito.`when`(
            kitsuCollectionDataSource.getTopRatedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            mangaByKitsuRepository.getTopRated(pageLimit, pageOffset)
                .blockingGet() as SafeApiRequest.ApiResultHandle.Success

        Mockito.verify(kitsuCollectionDataSource).getTopRatedCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset
        )

        Assert.assertEquals(expectedResponse, actualResponse.value)
    }

    @Test
    fun testGetTopRatedMangaApiError() {
        Mockito.`when`(
            kitsuCollectionDataSource.getTopRatedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        Mockito.`when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getTopRatedCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.just(SafeApiRequest.ApiResultHandle.ApiError))

        val actualResponse =
            mangaByKitsuRepository.getTopRated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetTopRatedMangaHttpException() {
        Mockito.`when`(
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

        Mockito.`when`(
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
            mangaByKitsuRepository.getTopRated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetTopRatedMangaIoException() {
        Mockito.`when`(
            kitsuCollectionDataSource.getTopRatedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.error(IOException()))

        Mockito.`when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getTopRatedCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.error(IOException()))

        val actualResponse =
            mangaByKitsuRepository.getTopRated(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.NetworkError)
    }

    @Test
    fun testGetPopularManga() {
        Mockito.`when`(
            kitsuCollectionDataSource.getPopularCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            mangaByKitsuRepository.getPopular(pageLimit, pageOffset)
                .blockingGet() as SafeApiRequest.ApiResultHandle.Success

        Mockito.verify(kitsuCollectionDataSource).getPopularCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset
        )

        Assert.assertEquals(expectedResponse, actualResponse.value)
    }

    @Test
    fun testGetPopularMangaApiError() {
        Mockito.`when`(
            kitsuCollectionDataSource.getPopularCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedResponse))

        Mockito.`when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getPopularCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.just(SafeApiRequest.ApiResultHandle.ApiError))

        val actualResponse =
            mangaByKitsuRepository.getPopular(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetPopularMangaHttpException() {
        Mockito.`when`(
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

        Mockito.`when`(
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
            mangaByKitsuRepository.getPopular(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetPopularMangaIoException() {
        Mockito.`when`(
            kitsuCollectionDataSource.getPopularCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.error(IOException()))

        Mockito.`when`(
            safeApiRequest.request<CollectionResponse> {
                kitsuCollectionDataSource.getPopularCollection(
                    pageLimit = pageLimit,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.error(IOException()))

        val actualResponse =
            mangaByKitsuRepository.getPopular(pageLimit, pageOffset)
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.NetworkError)
    }
}
