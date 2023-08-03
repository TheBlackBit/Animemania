package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuChaptersDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ChaptersResponse
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
class MangaChaptersByKitsuRepositoryTest {

    @Mock
    lateinit var kitsuChaptersDataSource: KitsuChaptersDataSource

    private lateinit var mangaChaptersByKitsuRepository: MangaChaptersByKitsuRepositoryImpl

    @Before
    fun setup() {
        mangaChaptersByKitsuRepository = MangaChaptersByKitsuRepositoryImpl(kitsuChaptersDataSource)
    }

    @Test
    fun testGetCollectionEpisodes() {
        val mangaId = "12345"
        val pageNumber = "1"
        val pageOffset = "0"
        val expectedEpisodeData = ChaptersResponse(chapterData = emptyList())

        `when`(
            kitsuChaptersDataSource.getChapters(
                mangaId = mangaId,
                pageNumber = pageNumber,
                pageOffset = pageOffset,
            ),
        ).thenReturn(Single.just(expectedEpisodeData))

        val actualEpisodesData =
            kitsuChaptersDataSource.getChapters(
                mangaId = mangaId,
                pageNumber = pageNumber,
                pageOffset = pageOffset,
            ).blockingGet()

        verify(kitsuChaptersDataSource).getChapters(
            mangaId = mangaId,
            pageNumber = pageNumber,
            pageOffset = pageOffset,
        )

        assertEquals(expectedEpisodeData, actualEpisodesData)
    }
}
