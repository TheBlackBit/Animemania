package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuChaptersDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ChaptersResponse
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
class MangaChaptersByKitsuRepositoryTest {

    @Mock
    lateinit var kitsuChaptersDataSource: KitsuChaptersDataSource

    @Mock
    lateinit var safeApiRequest: SafeApiRequest

    private lateinit var mangaChaptersByKitsuRepository: MangaChaptersByKitsuRepositoryImpl

    @Before
    fun setup() {
        mangaChaptersByKitsuRepository =
            MangaChaptersByKitsuRepositoryImpl(kitsuChaptersDataSource, safeApiRequest)
    }

    private val mangaId = "12345"
    private val pageNumber = "1"
    private val pageOffset = "0"
    private val expectedEpisodeData = ChaptersResponse(chapterData = emptyList())

    @Test
    fun testGetCollectionEpisodes() {
        `when`(
            kitsuChaptersDataSource.getChapters(
                mangaId = mangaId,
                pageLimit = pageNumber,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedEpisodeData))

        val actualEpisodesData =
            mangaChaptersByKitsuRepository.getMangaChapters(
                collectionId = mangaId,
                pageLimit = pageNumber,
                pageOffset = pageOffset
            ).blockingGet() as SafeApiRequest.ApiResultHandle.Success

        verify(kitsuChaptersDataSource).getChapters(
            mangaId = mangaId,
            pageLimit = pageNumber,
            pageOffset = pageOffset
        )

        assertEquals(expectedEpisodeData, actualEpisodesData.value)
    }

    @Test
    fun testGetCollectionEpisodesApiError() {
        `when`(
            kitsuChaptersDataSource.getChapters(
                mangaId = mangaId,
                pageLimit = pageNumber,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.just(expectedEpisodeData))

        `when`(
            safeApiRequest.request<ChaptersResponse> {
                mangaChaptersByKitsuRepository.getMangaChapters(
                    collectionId = mangaId,
                    pageLimit = pageNumber,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.just(SafeApiRequest.ApiResultHandle.ApiError))

        val actualResponse =
            mangaChaptersByKitsuRepository.getMangaChapters(
                collectionId = mangaId,
                pageLimit = pageNumber,
                pageOffset = pageOffset
            )
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetCollectionEpisodesHttpException() {
        `when`(
            kitsuChaptersDataSource.getChapters(
                mangaId = mangaId,
                pageLimit = pageNumber,
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
            safeApiRequest.request<ChaptersResponse> {
                mangaChaptersByKitsuRepository.getMangaChapters(
                    collectionId = mangaId,
                    pageLimit = pageNumber,
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
            mangaChaptersByKitsuRepository.getMangaChapters(
                collectionId = mangaId,
                pageLimit = pageNumber,
                pageOffset = pageOffset
            )
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.ApiError)
    }

    @Test
    fun testGetCollectionEpisodesIoException() {
        `when`(
            kitsuChaptersDataSource.getChapters(
                mangaId = mangaId,
                pageLimit = pageNumber,
                pageOffset = pageOffset
            )
        ).thenReturn(Single.error(IOException()))

        `when`(
            safeApiRequest.request<ChaptersResponse> {
                mangaChaptersByKitsuRepository.getMangaChapters(
                    collectionId = mangaId,
                    pageLimit = pageNumber,
                    pageOffset = pageOffset
                ).map { it }
            }
        ).thenReturn(Single.error(IOException()))

        val actualResponse =
            mangaChaptersByKitsuRepository.getMangaChapters(
                collectionId = mangaId,
                pageLimit = pageNumber,
                pageOffset = pageOffset
            )
                .blockingGet()

        assert(actualResponse is SafeApiRequest.ApiResultHandle.NetworkError)
    }
}
