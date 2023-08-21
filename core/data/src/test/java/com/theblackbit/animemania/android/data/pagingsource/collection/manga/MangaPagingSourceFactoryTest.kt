package com.theblackbit.animemania.android.data.pagingsource.collection.manga

import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.external.repository.MangaRemoteRepository
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.collection.EmptyCollectionPagingSource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MangaPagingSourceFactoryTest {

    @Mock
    private lateinit var collectionLocalRepository: CollectionLocalRepository

    @Mock
    private lateinit var collectionRemoteRepository: MangaRemoteRepository

    private lateinit var factory: MangaPagingSourceFactory

    @Before
    fun setUp() {
        factory = MangaPagingSourceFactory(
            collectionLocalRepository,
            collectionRemoteRepository
        )
    }

    @Test
    fun `getMangaPagingSource should return correct PagingSource for TRENDING_Manga_ID`() {
        val pagingSource = factory.getMangaPagingSource(RequestType.TRENDING_MANGA)

        assertEquals(TrendingMangaPagingSource::class.java, pagingSource.javaClass)
    }

    @Test
    fun `getMangaPagingSource should return correct PagingSource for MOST_ANTICIPATED_Manga_ID`() {
        val pagingSource = factory.getMangaPagingSource(RequestType.MOST_ANTICIPATED_MANGA)

        assertEquals(MostAnticipatedMangaPagingSource::class.java, pagingSource.javaClass)
    }

    @Test
    fun `getMangaPagingSource should return correct PagingSource for TOP_RATED_Manga_ID`() {
        val pagingSource = factory.getMangaPagingSource(RequestType.TOP_RATED_MANGA)

        assertEquals(TopRatedMangaPagingSource::class.java, pagingSource.javaClass)
    }

    @Test
    fun `getMangaPagingSource should return correct PagingSource for POPULAR_Manga_ID`() {
        val pagingSource = factory.getMangaPagingSource(RequestType.POPULAR_MANGA)

        assertEquals(PopularMangaPagingSource::class.java, pagingSource.javaClass)
    }

    @Test
    fun `getMangaPagingSource should return EmptyCollectionPagingSource for unknown category ID`() {
        val pagingSource = factory.getMangaPagingSource(RequestType.TRENDING_ANIME)

        assertEquals(EmptyCollectionPagingSource::class.java, pagingSource.javaClass)
    }
}
