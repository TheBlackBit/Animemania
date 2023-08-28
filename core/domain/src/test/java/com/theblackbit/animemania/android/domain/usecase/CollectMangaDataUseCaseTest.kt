package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingConfig
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.pagingsource.collection.manga.MangaPagingSourceFactory
import com.theblackbit.animemania.android.data.pagingsource.collection.manga.TrendingMangaPagingSource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CollectMangaDataUseCaseTest {

    @Mock
    private lateinit var pagingSourceFactory: MangaPagingSourceFactory

    @Mock
    private lateinit var pagingConfig: PagingConfig

    private lateinit var collectMangaDataUseCase: CollectMangaDataUseCase

    @Mock
    private lateinit var trendingMangaPagingSource: TrendingMangaPagingSource

    @Before
    fun setup() {
        collectMangaDataUseCase = CollectMangaDataUseCase(pagingSourceFactory, pagingConfig)
        val requestType = RequestType.TRENDING_ANIME

        Mockito.`when`(pagingSourceFactory.getMangaPagingSource(requestType))
            .thenReturn(trendingMangaPagingSource)

        Mockito.`when`(trendingMangaPagingSource.jumpingSupported)
            .thenReturn(true)
    }

    @Test
    fun `test collectByCategory`() {
        collectMangaDataUseCase.collectByCategory(RequestType.TRENDING_ANIME)
            .subscribe()

        Mockito.verify(pagingSourceFactory).getMangaPagingSource(RequestType.TRENDING_ANIME)
    }
}
