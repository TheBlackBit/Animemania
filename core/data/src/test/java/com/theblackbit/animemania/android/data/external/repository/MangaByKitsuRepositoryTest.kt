package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuCollectionDataSource
import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuMediaType
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
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

    private lateinit var mangaByKitsuRepository: MangaByKitsuRepository

    @Mock
    lateinit var kitsuCollectionDataSource: KitsuCollectionDataSource

    private val pageLimit = "10"
    private val pageOffset = "0"
    private val expectedResponse = CollectionResponse(emptyList())

    @Before
    fun setup() {
        mangaByKitsuRepository = MangaByKitsuRepository(kitsuCollectionDataSource)
    }

    @Test
    fun testCollectTrendingManga() {
        Mockito.`when`(
            kitsuCollectionDataSource.getTrendingCollection(
                path = KitsuMediaType.MANGA_TRENDING,
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            mangaByKitsuRepository.collectTrendingCollection(pageLimit, pageOffset).blockingGet()

        Mockito.verify(kitsuCollectionDataSource).getTrendingCollection(
            path = KitsuMediaType.MANGA_TRENDING,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        Assert.assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun testGetMostWantedMANGA() {
        Mockito.`when`(
            kitsuCollectionDataSource.getMostWantedCollection(
                path = KitsuMediaType.MANGA_MEDIA_TYPE,
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            mangaByKitsuRepository.getMostWantedCollection(pageLimit, pageOffset).blockingGet()

        Mockito.verify(kitsuCollectionDataSource).getMostWantedCollection(
            path = KitsuMediaType.MANGA_MEDIA_TYPE,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        Assert.assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun testGetTopRatedManga() {
        Mockito.`when`(
            kitsuCollectionDataSource.getTopRatedCollection(
                path = KitsuMediaType.MANGA_MEDIA_TYPE,
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            mangaByKitsuRepository.getTopRatedCollection(pageLimit, pageOffset).blockingGet()

        Mockito.verify(kitsuCollectionDataSource).getTopRatedCollection(
            path = KitsuMediaType.MANGA_MEDIA_TYPE,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        Assert.assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun testGetPopularManga() {
        Mockito.`when`(
            kitsuCollectionDataSource.getPopularCollection(
                path = KitsuMediaType.MANGA_MEDIA_TYPE,
                pageLimit = pageLimit,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedResponse))

        val actualResponse =
            mangaByKitsuRepository.getPopularCollection(pageLimit, pageOffset).blockingGet()

        Mockito.verify(kitsuCollectionDataSource).getPopularCollection(
            path = KitsuMediaType.MANGA_MEDIA_TYPE,
            pageLimit = pageLimit,
            pageOffset = pageOffset,
        )

        Assert.assertEquals(expectedResponse, actualResponse)
    }
}
