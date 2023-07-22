package com.theblackbit.animemania.android.home

import androidx.paging.PagingData
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.util.CategoryHolder
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
        sut = CollectionViewModel(collectAnimeDataUseCase, collectMangaDataUseCase)
    }

    @Test
    fun testStartToCollectAnimeData(): Unit = runBlocking {
        Mockito.`when`(collectAnimeDataUseCase.collectByCategory(CategoryHolder.POPULAR))
            .thenReturn(getTestFlowable())

        sut.startToCollectAnimeData(CategoryHolder.POPULAR, testScope)

        Mockito.verify(collectAnimeDataUseCase).collectByCategory(CategoryHolder.POPULAR)
    }

    @Test
    fun testStartToCollectMangaData(): Unit = runBlocking {
        Mockito.`when`(collectMangaDataUseCase.collectByCategory(CategoryHolder.POPULAR))
            .thenReturn(getTestFlowable())

        sut.startToCollectMangaData(CategoryHolder.POPULAR, testScope)

        Mockito.verify(collectMangaDataUseCase).collectByCategory(CategoryHolder.POPULAR)
    }

    private fun getTestFlowable(): Flowable<PagingData<Collection>> {
        val testData: List<Collection> = emptyList()
        val pagingData = PagingData.from(testData)
        return Flowable.just(pagingData)
    }
}
