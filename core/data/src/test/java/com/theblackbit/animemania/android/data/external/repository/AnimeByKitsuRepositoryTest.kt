package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuAnimeDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
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
    fun testCollectTrendingAnime() {
        `when`(
            kitsuCollectionDataSource.getTrendingCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.collectTrending(pageLimit, pageOffset)
                .blockingGet() as SafeApiRequest.ApiResultHandle.Success

        verify(kitsuCollectionDataSource).getTrendingCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        assertEquals(expectedResponse, actualResponse.value)
    }

    @Test
    fun testGetMostWantedAnime() {
        `when`(
            kitsuCollectionDataSource.getMostWantedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.getMostAnticipated(pageLimit, pageOffset)
                .blockingGet() as SafeApiRequest.ApiResultHandle.Success

        verify(kitsuCollectionDataSource).getMostWantedCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        assertEquals(expectedResponse, actualResponse.value)
    }

    @Test
    fun testGetTopRatedAnime() {
        `when`(
            kitsuCollectionDataSource.getTopRatedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.getTopRated(pageLimit, pageOffset)
                .blockingGet() as SafeApiRequest.ApiResultHandle.Success

        verify(kitsuCollectionDataSource).getTopRatedCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        assertEquals(expectedResponse, actualResponse.value)
    }

    @Test
    fun testGetPopularAnime() {
        `when`(
            kitsuCollectionDataSource.getPopularCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.getPopular(pageLimit, pageOffset).blockingGet()
                as SafeApiRequest.ApiResultHandle.Success

        verify(kitsuCollectionDataSource).getPopularCollection(
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        assertEquals(expectedResponse, actualResponse.value)
    }
}
