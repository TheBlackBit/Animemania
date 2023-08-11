package com.theblackbit.animemania.android.data.pagingsource.collection.anime

import com.theblackbit.animemania.android.data.external.datasource.categories.MOST_ANTICIPATED_ANIME_ID
import com.theblackbit.animemania.android.data.external.datasource.categories.POPULAR_ANIME_ID
import com.theblackbit.animemania.android.data.external.datasource.categories.TOP_RATED_ANIME_ID
import com.theblackbit.animemania.android.data.external.datasource.categories.TRENDING_ANIME_ID
import com.theblackbit.animemania.android.data.external.repository.AnimeRemoteRepository
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.collection.EmptyCollectionPagingSource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AnimePagingSourceFactoryTest {

    @Mock
    private lateinit var collectionLocalRepository: CollectionLocalRepository

    @Mock
    private lateinit var collectionRemoteRepository: AnimeRemoteRepository

    private lateinit var factory: AnimePagingSourceFactory

    @Before
    fun setUp() {
        factory = AnimePagingSourceFactory(
            collectionLocalRepository,
            collectionRemoteRepository,
        )
    }

    @Test
    fun `getAnimePagingSource should return correct PagingSource for TRENDING_ANIME_ID`() {
        val categoryId = TRENDING_ANIME_ID
        val pagingSource = factory.getAnimePagingSource(categoryId)

        assertEquals(TrendingAnimePagingSource::class.java, pagingSource.javaClass)
    }

    @Test
    fun `getAnimePagingSource should return correct PagingSource for MOST_ANTICIPATED_ANIME_ID`() {
        val categoryId = MOST_ANTICIPATED_ANIME_ID
        val pagingSource = factory.getAnimePagingSource(categoryId)

        assertEquals(MostAnticipatedAnimePagingSource::class.java, pagingSource.javaClass)
    }

    @Test
    fun `getAnimePagingSource should return correct PagingSource for TOP_RATED_ANIME_ID`() {
        val categoryId = TOP_RATED_ANIME_ID
        val pagingSource = factory.getAnimePagingSource(categoryId)

        assertEquals(TopRatedAnimePagingSource::class.java, pagingSource.javaClass)
    }

    @Test
    fun `getAnimePagingSource should return correct PagingSource for POPULAR_ANIME_ID`() {
        val categoryId = POPULAR_ANIME_ID
        val pagingSource = factory.getAnimePagingSource(categoryId)

        assertEquals(PopularAnimePagingSource::class.java, pagingSource.javaClass)
    }

    @Test
    fun `getAnimePagingSource should return EmptyCollectionPagingSource for unknown category ID`() {
        val categoryId = 999
        val pagingSource = factory.getAnimePagingSource(categoryId)

        assertEquals(EmptyCollectionPagingSource::class.java, pagingSource.javaClass)
    }
}
