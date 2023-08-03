package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuCollectionDataSource
import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuMediaType.ANIME_MEDIA_TYPE
import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuMediaType.ANIME_TRENDING
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
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

    private lateinit var animeByKitsuRepository: AnimeByKitsuRepository

    @Mock
    lateinit var kitsuCollectionDataSource: KitsuCollectionDataSource

    private val pageLimit = "10"
    private val pageOffset = "0"
    private val expectedResponse = CollectionResponse(emptyList())

    @Before
    fun setup() {
        animeByKitsuRepository = AnimeByKitsuRepository(kitsuCollectionDataSource)
    }

    @Test
    fun testCollectTrendingAnime() {
        `when`(
            kitsuCollectionDataSource.getTrendingCollection(
                path = ANIME_TRENDING,
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.collectTrendingCollection(pageLimit, pageOffset).blockingGet()

        verify(kitsuCollectionDataSource).getTrendingCollection(
            path = ANIME_TRENDING,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun testGetMostWantedAnime() {
        `when`(
            kitsuCollectionDataSource.getMostWantedCollection(
                path = ANIME_MEDIA_TYPE,
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.getMostWantedCollection(pageLimit, pageOffset).blockingGet()

        verify(kitsuCollectionDataSource).getMostWantedCollection(
            path = ANIME_MEDIA_TYPE,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun testGetTopRatedAnime() {
        `when`(
            kitsuCollectionDataSource.getTopRatedCollection(
                path = ANIME_MEDIA_TYPE,
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.getTopRatedCollection(pageLimit, pageOffset).blockingGet()

        verify(kitsuCollectionDataSource).getTopRatedCollection(
            path = ANIME_MEDIA_TYPE,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun testGetPopularAnime() {
        `when`(
            kitsuCollectionDataSource.getPopularCollection(
                path = ANIME_MEDIA_TYPE,
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            animeByKitsuRepository.getPopularCollection(pageLimit, pageOffset).blockingGet()

        verify(kitsuCollectionDataSource).getPopularCollection(
            path = ANIME_MEDIA_TYPE,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        assertEquals(expectedResponse, actualResponse)
    }
}
