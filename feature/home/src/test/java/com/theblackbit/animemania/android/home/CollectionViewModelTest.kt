package com.theblackbit.animemania.android.home

import androidx.paging.PagingData
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CollectionViewModelTest {

    @Mock
    private lateinit var collectAnimeDataUseCase: CollectCollectionDataUseCase

    @Mock
    private lateinit var collectMangaDataUseCase: CollectCollectionDataUseCase

    private lateinit var sut: CollectionViewModel

    private val testScope = TestScope()

    @Before
    fun setUp() {
        sut = CollectionViewModel(
            collectAnimeDataUseCase,
            collectMangaDataUseCase,
        )
    }

    @Test
    fun testStartToCollectAnimeData(): Unit = runBlocking {
        Mockito.`when`(collectAnimeDataUseCase.collectByCategory(RequestType.TRENDING_ANIME))
            .thenReturn(getTestFlowable())

        sut.startToCollectAnimeData(RequestType.TRENDING_ANIME, testScope)

        Mockito.verify(collectAnimeDataUseCase).collectByCategory(RequestType.TRENDING_ANIME)
    }

    @Test
    fun testStartToCollectMangaData(): Unit = runBlocking {
        Mockito.`when`(collectMangaDataUseCase.collectByCategory(RequestType.TRENDING_MANGA))
            .thenReturn(getTestFlowable())

        sut.startToCollectMangaData(RequestType.TRENDING_MANGA, testScope)

        Mockito.verify(collectMangaDataUseCase).collectByCategory(RequestType.TRENDING_MANGA)
    }

    private fun getTestFlowable(): Flowable<PagingData<Collection>> {
        val testData: List<Collection> = emptyList()
        val pagingData = PagingData.from(testData)
        return Flowable.just(pagingData)
    }
}
