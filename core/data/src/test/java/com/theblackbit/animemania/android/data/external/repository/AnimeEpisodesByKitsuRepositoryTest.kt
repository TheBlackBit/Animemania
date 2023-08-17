package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuEpisodesDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodesResponse
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

    @Test
    fun testGetCollectionEpisodes() {
        val collectionId = "12345"
        val pageNumber = "1"
        val pageOffset = "0"
        val expectedEpisodeData = EpisodesResponse(episodeData = emptyList())

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
}
