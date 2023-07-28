package com.theblackbit.animemania.android.home

import androidx.paging.PagingData
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.model.Category
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
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

    @Mock
    private lateinit var collectAnimeCategoriesUseCase: CollectCategoriesUseCase

    @Mock
    private lateinit var collectMangaCategoriesUseCase: CollectCategoriesUseCase

    private lateinit var sut: CollectionViewModel

    private val testScope = TestScope()

    private val listOfCategories = listOf(
        Category(1, "test1"),
        Category(2, "test2"),
    )

    @Before
    fun setUp() {
        sut = CollectionViewModel(
            collectAnimeDataUseCase,
            collectMangaDataUseCase,
            collectAnimeCategoriesUseCase,
            collectMangaCategoriesUseCase,
        )
    }

    @Test
    fun testStartToCollectAnimeCategories() {
        Mockito.`when`(collectAnimeCategoriesUseCase.collect())
            .thenReturn(Single.just(listOfCategories))

        val result = sut.startToCollectAnimeCategories()

        result.test().assertResult(listOfCategories)
    }

    @Test
    fun testStartToCollectMangaCategories() {
        Mockito.`when`(collectMangaCategoriesUseCase.collect())
            .thenReturn(Single.just(listOfCategories))

        val result = sut.startToCollectMangaCategories()

        result.test().assertResult(listOfCategories)
    }

    @Test
    fun testStartToCollectAnimeData(): Unit = runBlocking {
        Mockito.`when`(collectAnimeDataUseCase.collectByCategory(1))
            .thenReturn(getTestFlowable())

        sut.startToCollectAnimeData(1, testScope)

        Mockito.verify(collectAnimeDataUseCase).collectByCategory(1)
    }

    @Test
    fun testStartToCollectMangaData(): Unit = runBlocking {
        Mockito.`when`(collectMangaDataUseCase.collectByCategory(1))
            .thenReturn(getTestFlowable())

        sut.startToCollectMangaData(1, testScope)

        Mockito.verify(collectMangaDataUseCase).collectByCategory(1)
    }

    private fun getTestFlowable(): Flowable<PagingData<Collection>> {
        val testData: List<Collection> = emptyList()
        val pagingData = PagingData.from(testData)
        return Flowable.just(pagingData)
    }
}
