package com.theblackbit.animemania.android.data.pagingsource.chapter

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.testing.TestPager
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodeAttributes
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodeData
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodesResponse
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.toChapterEntity
import com.theblackbit.animemania.android.data.external.repository.EpisodesByKitsuRepository
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.toChapterModel
import com.theblackbit.animemania.android.data.internal.repository.ChapterLocalRepository
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class EpisodePagingSourceTest {

    private lateinit var sut: EpisodePagingSource

    @Mock
    private lateinit var localRepository: ChapterLocalRepository

    @Mock
    private lateinit var remoteRepository: EpisodesByKitsuRepository

    private val collectionId = "1"

    private val response by lazy {
        fakeResponse()
    }

    @Test
    fun testRefreshKey() = runTest {
        sut = EpisodePagingSource(
            localRepository = localRepository,
            remoteRepository = remoteRepository,
            collectionId = collectionId
        )

        val result = sut.getRefreshKey(
            PagingState(
                pages = emptyList(),
                anchorPosition = 1,
                config = PagingConfig(
                    pageSize = 10
                ),
                leadingPlaceholderCount = 0
            )
        )

        Assert.assertEquals(result, 1)
    }

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runTest {
        Mockito.`when`(
            /* methodCall = */ remoteRepository.getCollectionEpisodes(
                collectionId,
                pageNumber = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT.toString(),
                pageOffset = "null"
            )
        ).thenReturn(
            Single.just(SafeApiRequest.ApiResultHandle.Success(response))
        )

        val episodeEntities = response.episodeData.map {
            it.toChapterEntity(page = 1, collectionId = collectionId)
        }

        val episodeModel = episodeEntities.map {
            it.toChapterModel()
        }

        sut = EpisodePagingSource(
            localRepository = localRepository,
            remoteRepository = remoteRepository,
            collectionId = collectionId
        )

        Mockito.`when`(
            localRepository.getChaptersByCollection(
                collectionId,
                pageNumber = 1
            )
        ).thenReturn(
            Single.just(
                episodeEntities
            )
        )

        val pager = TestPager(
            config = PagingConfig(
                pageSize = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT
            ),
            pagingSource = sut
        )

        val page = with(pager) {
            refresh()
        } as PagingSource.LoadResult.Page

        Mockito.verify(localRepository).deleteChaptersByCollection(collectionId)

        Mockito.verify(localRepository).insertChapters(episodeEntities)

        assert(page.data.containsAll(episodeModel))
    }

    @Test
    fun whenLoadSuccessPage2DoesNotClearCache() = runTest {
        val pageNumber = 2

        Mockito.`when`(
            /* methodCall = */ remoteRepository.getCollectionEpisodes(
                collectionId,
                pageNumber = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT.toString(),
                pageOffset = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT.toString()
            )
        ).thenReturn(
            Single.just(SafeApiRequest.ApiResultHandle.Success(response))
        )

        val episodeEntities = response.episodeData.map {
            it.toChapterEntity(page = pageNumber, collectionId = collectionId)
        }

        val episodeModel = episodeEntities.map {
            it.toChapterModel()
        }

        sut = EpisodePagingSource(
            localRepository = localRepository,
            remoteRepository = remoteRepository,
            collectionId = collectionId
        )

        Mockito.`when`(
            localRepository.getChaptersByCollection(
                collectionId,
                pageNumber = pageNumber
            )
        ).thenReturn(
            Single.just(
                episodeEntities
            )
        )

        val pager = TestPager(
            config = PagingConfig(
                pageSize = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT
            ),
            pagingSource = sut
        )

        val page = with(pager) {
            refresh(initialKey = pageNumber)
        } as PagingSource.LoadResult.Page

        Mockito.verify(localRepository, Mockito.times(0))
            .deleteChaptersByCollection(collectionId)

        Mockito.verify(localRepository).insertChapters(episodeEntities)

        assert(page.data.containsAll(episodeModel))
    }

    @Test
    fun whenApiLoadErrorReturnsDataFromCache() = runTest {
        val pageNumber = 1

        Mockito.`when`(
            /* methodCall = */ remoteRepository.getCollectionEpisodes(
                collectionId,
                pageNumber = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT.toString(),
                pageOffset = "null"
            )
        ).thenReturn(
            Single.just(SafeApiRequest.ApiResultHandle.ApiError)
        )

        val episodeEntities = response.episodeData.map {
            it.toChapterEntity(page = pageNumber, collectionId = collectionId)
        }

        val episodeModel = episodeEntities.map {
            it.toChapterModel()
        }

        sut = EpisodePagingSource(
            localRepository = localRepository,
            remoteRepository = remoteRepository,
            collectionId = collectionId
        )

        Mockito.`when`(
            localRepository.getChaptersByCollection(collectionId, pageNumber)
        ).thenReturn(
            Single.just(
                episodeEntities
            )
        )

        val pager = TestPager(
            config = PagingConfig(
                pageSize = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT
            ),
            pagingSource = sut
        )

        val page = with(pager) {
            refresh(initialKey = pageNumber)
        } as PagingSource.LoadResult.Page

        Mockito.verify(localRepository, Mockito.times(0))
            .deleteChaptersByCollection(collectionId)

        Mockito.verify(localRepository, Mockito.times(0))
            .insertChapters(episodeEntities)

        assert(page.data.containsAll(episodeModel))
    }

    @Test
    fun testApiAndCacheReturnsEmptyData() = runTest {
        val pageNumber = 1

        Mockito.`when`(
            /* methodCall = */ remoteRepository.getCollectionEpisodes(
                collectionId,
                pageNumber = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT.toString(),
                "null"
            )
        ).thenReturn(
            Single.just(SafeApiRequest.ApiResultHandle.ApiError)
        )

        sut = EpisodePagingSource(
            localRepository = localRepository,
            remoteRepository = remoteRepository,
            collectionId = collectionId
        )

        Mockito.`when`(
            localRepository.getChaptersByCollection(collectionId, pageNumber)
        ).thenReturn(
            Single.just(
                emptyList()
            )
        )

        val pager = TestPager(
            config = PagingConfig(
                pageSize = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT
            ),
            pagingSource = sut
        )

        val page = with(pager) {
            refresh(initialKey = pageNumber)
        } as PagingSource.LoadResult.Page

        assert(page.data.isEmpty())
        Assert.assertEquals(page.nextKey, null)
    }

    private fun fakeResponse(): EpisodesResponse {
        val chapters: ArrayList<EpisodeData> = ArrayList()
        for (i in 0 until ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT) {
            EpisodeData(
                id = i.toString(),
                attributes = EpisodeAttributes(
                    canonicalTitle = "Test",
                    number = i + 1,
                    seasonNumber = 1,
                    synopsis = "Test",
                    thumbnail = null
                )
            )
        }
        return EpisodesResponse(chapters)
    }
}
