package com.theblackbit.animemania.android.data.pagingsource.collection

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.theblackbit.animemania.android.data.datatest.collectionDataList
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.toCollectionEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionCategoryJoin
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionCategoryJoinEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.toCollection
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
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

    private val categoryId = 1

    private val collectionResponse = CollectionResponse(
        collectionData = collectionDataList,
    )

    private val collectionEntities = collectionDataList.map {
        it.toCollectionEntity(categoryId = categoryId, collectionType = "Anime")
    }

    private val animeList = collectionEntities.map {
        it.toCollection()
    }

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runTest {
        collectionPagingSource = CollectionPagingSource(
            localRepository = localRepository,
            request = { _, _ ->
                Single.just(SafeApiRequest.ApiResultHandle.Success(collectionResponse))
            },
            categoryId = 1,
        )
        val pageNumber = 1

        val collectionCategoriesJoinEntities = mapCollectionEntityToJoinCollectionEntity(pageNumber)

        Mockito.`when`(
            localRepository.collectPagedCollectionsByCategory(pageNumber, categoryId),
        ).thenReturn(
            Single.just(
                CollectionCategoryJoin(
                    collectionCategoryJoinEntity = CollectionCategoryJoinEntity(
                        categoryId = categoryId,
                        collectionId = 1,
                        pageNumber = pageNumber,
                    ),
                    collections = collectionEntities,
                ),
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

        Mockito.verify(localRepository).clearCollectioncategoryjoinentity(categoryId)

        Mockito.verify(localRepository).clearCollectionEntitiesByCategory(categoryId)

        Mockito.verify(localRepository).insertCollectionEntities(collectionEntities)

        Mockito.verify(localRepository)
            .insertCollectionCategoryJoinEntities(collectionCategoriesJoinEntities)

        assert(page.data.containsAll(animeList))
    }

    @Test
    fun whenLoadSuccessPage2DoesNotClearCache() = runTest {
        val pageNumber = 2

        val collectionCategoriesJoinEntities = mapCollectionEntityToJoinCollectionEntity(pageNumber)

        collectionPagingSource = CollectionPagingSource(
            localRepository = localRepository,
            request = { _, _ ->
                Single.just(SafeApiRequest.ApiResultHandle.Success(collectionResponse))
            },
            categoryId = 1,
        )

        Mockito.`when`(
            localRepository.collectPagedCollectionsByCategory(pageNumber, categoryId),
        ).thenReturn(
            Single.just(
                CollectionCategoryJoin(
                    collectionCategoryJoinEntity = CollectionCategoryJoinEntity(
                        categoryId = categoryId,
                        collectionId = 1,
                        pageNumber = pageNumber,
                    ),
                    collections = collectionEntities,
                ),
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

        Mockito.verify(localRepository, times(0)).clearCollectioncategoryjoinentity(categoryId)

        Mockito.verify(localRepository, times(0)).clearCollectionEntitiesByCategory(categoryId)

        Mockito.verify(localRepository).insertCollectionEntities(collectionEntities)

        Mockito.verify(localRepository)
            .insertCollectionCategoryJoinEntities(collectionCategoriesJoinEntities)

        assert(page.data.containsAll(animeList))
    }

    @Test
    fun whenApiLoadErrorReturnsDataFromCache() = runTest {
        val pageNumber = 1

        val collectionCategoriesJoinEntities = collectionEntities.map {
            CollectionCategoryJoinEntity(
                collectionId = it.collectionId,
                categoryId = categoryId,
                pageNumber = pageNumber,
            )
        }

        collectionPagingSource = CollectionPagingSource(
            localRepository = localRepository,
            request = { _, _ ->
                Single.just(SafeApiRequest.ApiResultHandle.ApiError)
            },
            categoryId = 1,
        )

        Mockito.`when`(
            localRepository.collectPagedCollectionsByCategory(pageNumber, categoryId),
        ).thenReturn(
            Single.just(
                CollectionCategoryJoin(
                    collectionCategoryJoinEntity = CollectionCategoryJoinEntity(
                        categoryId = categoryId,
                        collectionId = 1,
                        pageNumber = pageNumber,
                    ),
                    collections = collectionEntities,
                ),
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

        Mockito.verify(localRepository, times(0)).clearCollectioncategoryjoinentity(categoryId)

        Mockito.verify(localRepository, times(0)).clearCollectionEntitiesByCategory(categoryId)

        Mockito.verify(localRepository, times(0)).insertCollectionEntities(collectionEntities)

        Mockito.verify(localRepository, times(0))
            .insertCollectionCategoryJoinEntities(collectionCategoriesJoinEntities)

        assert(page.data.containsAll(animeList))
    }

    private fun mapCollectionEntityToJoinCollectionEntity(pageNumber: Int): List<CollectionCategoryJoinEntity> {
        return collectionEntities.map {
            CollectionCategoryJoinEntity(
                collectionId = it.collectionId,
                categoryId = categoryId,
                pageNumber = pageNumber,
            )
        }
    }
}
