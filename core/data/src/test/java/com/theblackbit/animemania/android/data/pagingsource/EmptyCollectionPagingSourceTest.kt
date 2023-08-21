package com.theblackbit.animemania.android.data.pagingsource

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.testing.TestPager
import com.theblackbit.animemania.android.data.pagingsource.collection.EmptyCollectionPagingSource
import com.theblackbit.animemania.android.model.Collection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class EmptyCollectionPagingSourceTest {

    private val emptyCollectionPagingSource = EmptyCollectionPagingSource()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testEmptyCollectionPagingSourceRefreshKey() = runTest {
        val result = emptyCollectionPagingSource.getRefreshKey(
            PagingState(
                pages = emptyList(),
                anchorPosition = 1,
                config = PagingConfig(
                    pageSize = 10
                ),
                leadingPlaceholderCount = 0
            )
        )

        assertEquals(result, 1)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testEmptyCollectionPagingOnRefreshReturnError() = runTest {
        val pager = TestPager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSource = emptyCollectionPagingSource
        )

        val result = pager.refresh()

        assert(result is PagingSource.LoadResult.Error<Int, Collection>)
    }
}
