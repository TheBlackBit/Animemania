package com.theblackbit.animemania.android.data.pagingsource.collection

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.theblackbit.animemania.android.data.datatest.collectionDataList
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.toCollectionEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.toCollection
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.model.CollectionType
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CollectionPagingSourceTest {

    @Mock
    private lateinit var localRepository: CollectionLocalRepository

    private lateinit var collectionPagingSource: CollectionPagingSource

    private val pageLimit = 20

    private val collectionResponse = CollectionResponse(
        collectionData = collectionDataList,
    )

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runTest {
        val collectionEntities = collectionResponse.collectionData.map {
            it.toCollectionEntity(
                collectionType = CollectionType.ANIME.collectionName,
                typeOfRequest = RequestType.TRENDING_ANIME,
                page = 1,
            )
        }

        val animeList = collectionEntities.map {
            it.toCollection(collectionType = CollectionType.ANIME)
        }

        collectionPagingSource = CollectionPagingSource(
            localRepository = localRepository,
            request = { _, _ ->
                Single.just(SafeApiRequest.ApiResultHandle.Success(collectionResponse))
            },
            requestType = RequestType.TRENDING_ANIME,
            collectionType = CollectionType.ANIME,
        )
        val pageNumber = 1

        Mockito.`when`(
            localRepository.collectPagedCollections(
                pageNumber,
                requestType = RequestType.TRENDING_ANIME,
            ),
        ).thenReturn(
            Single.just(
                collectionEntities,
            ),
        )

        val pager = TestPager(
            config = PagingConfig(
                pageSize = pageLimit,
            ),
            pagingSource = collectionPagingSource,
        )

        val page = with(pager) {
            refresh(initialKey = pageNumber)
        } as PagingSource.LoadResult.Page

        Mockito.verify(localRepository).clearCollectionsByRequestType(RequestType.TRENDING_ANIME)

        Mockito.verify(localRepository).insertCollectionsEntities(collectionEntities)

        assert(page.data.containsAll(animeList))
    }

    @Test
    fun whenLoadSuccessPage2DoesNotClearCache() = runTest {
        val pageNumber = 2

        val collectionEntities = collectionResponse.collectionData.map {
            it.toCollectionEntity(
                collectionType = CollectionType.ANIME.collectionName,
                typeOfRequest = RequestType.TRENDING_ANIME,
                page = pageNumber,
            )
        }

        val animeList = collectionEntities.map {
            it.toCollection(collectionType = CollectionType.ANIME)
        }

        collectionPagingSource = CollectionPagingSource(
            localRepository = localRepository,
            request = { _, _ ->
                Single.just(SafeApiRequest.ApiResultHandle.Success(collectionResponse))
            },
            requestType = RequestType.TRENDING_ANIME,
            collectionType = CollectionType.ANIME,
        )

        Mockito.`when`(
            localRepository.collectPagedCollections(
                pageNumber,
                requestType = RequestType.TRENDING_ANIME,
            ),
        ).thenReturn(
            Single.just(
                collectionEntities,
            ),
        )

        val pager = TestPager(
            config = PagingConfig(
                pageSize = pageLimit,
            ),
            pagingSource = collectionPagingSource,
        )

        val page = with(pager) {
            refresh(initialKey = pageNumber)
        } as PagingSource.LoadResult.Page

        Mockito.verify(localRepository, times(0))
            .clearCollectionsByRequestType(RequestType.TRENDING_ANIME)

        Mockito.verify(localRepository).insertCollectionsEntities(collectionEntities)

        assert(page.data.containsAll(animeList))
    }

    @Test
    fun whenApiLoadErrorReturnsDataFromCache() = runTest {
        val pageNumber = 1

        val collectionEntities = collectionResponse.collectionData.map {
            it.toCollectionEntity(
                collectionType = CollectionType.ANIME.collectionName,
                typeOfRequest = RequestType.TRENDING_ANIME,
                page = pageNumber,
            )
        }

        val animeList = collectionEntities.map {
            it.toCollection(collectionType = CollectionType.ANIME)
        }

        collectionPagingSource = CollectionPagingSource(
            localRepository = localRepository,
            request = { _, _ ->
                Single.just(SafeApiRequest.ApiResultHandle.ApiError)
            },
            requestType = RequestType.TRENDING_ANIME,
            collectionType = CollectionType.ANIME,
        )

        Mockito.`when`(
            localRepository.collectPagedCollections(pageNumber, RequestType.TRENDING_ANIME),
        ).thenReturn(
            Single.just(
                collectionEntities,
            ),
        )

        val pager = TestPager(
            config = PagingConfig(
                pageSize = pageLimit,
            ),
            pagingSource = collectionPagingSource,
        )

        val page = with(pager) {
            refresh(initialKey = pageNumber)
        } as PagingSource.LoadResult.Page

        Mockito.verify(localRepository, times(0))
            .clearCollectionsByRequestType(RequestType.TRENDING_ANIME)

        Mockito.verify(localRepository, times(0))
            .insertCollectionsEntities(collectionEntities)

        assert(page.data.containsAll(animeList))
    }
}
