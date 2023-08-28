package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingConfig
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.pagingsource.collection.anime.AnimePagingSourceFactory
import com.theblackbit.animemania.android.data.pagingsource.collection.anime.TrendingAnimePagingSource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CollectAnimeDataUseCaseTest {

    @Mock
    private lateinit var pagingSourceFactory: AnimePagingSourceFactory

    @Mock
    private lateinit var pagingConfig: PagingConfig

    private lateinit var collectAnimeDataUseCase: CollectAnimeDataUseCase

    @Mock
    private lateinit var trendingAnimePagingSource: TrendingAnimePagingSource

    @Before
    fun setup() {
        collectAnimeDataUseCase = CollectAnimeDataUseCase(pagingSourceFactory, pagingConfig)
        val requestType = RequestType.TRENDING_ANIME

        Mockito.`when`(pagingSourceFactory.getAnimePagingSource(requestType))
            .thenReturn(trendingAnimePagingSource)

        Mockito.`when`(trendingAnimePagingSource.jumpingSupported)
            .thenReturn(true)
    }

    @Test
    fun `test collectByCategory`() {
        collectAnimeDataUseCase.collectByCategory(RequestType.TRENDING_ANIME)
            .subscribe()

        Mockito.verify(pagingSourceFactory).getAnimePagingSource(RequestType.TRENDING_ANIME)
    }
}
