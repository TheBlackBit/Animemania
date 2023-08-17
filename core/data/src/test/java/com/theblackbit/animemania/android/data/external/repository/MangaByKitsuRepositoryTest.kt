package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuMangaDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

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
            ),
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
    fun testGetMostWantedMANGA() {
        Mockito.`when`(
            kitsuCollectionDataSource.getMostWantedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            ),
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
    fun testGetTopRatedManga() {
        Mockito.`when`(
            kitsuCollectionDataSource.getTopRatedCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            ),
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
    fun testGetPopularManga() {
        Mockito.`when`(
            kitsuCollectionDataSource.getPopularCollection(
                pageLimit = pageLimit,
                pageOffset = pageOffset
            ),
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
}
