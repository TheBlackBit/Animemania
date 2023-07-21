package com.theblackbit.animemania.android.home

import androidx.paging.PagingData
import com.theblackbit.animemania.android.model.Data
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
class HomeTabContentFragmentViewModelTest {

    @Mock
    private lateinit var collectPagingDataUseCase: com.theblackbit.animemania.android.domain.usecase.CollectPagingDataUseCase

    private lateinit var sut: HomeTabContentFragmentViewModel

    private val testScope = TestScope()

    @Before
    fun setUp() {
        sut = HomeTabContentFragmentViewModel(collectPagingDataUseCase)
    }

    @Test
    fun testStartToCollectAnimeData(): Unit = runBlocking {
        val testData = createTestData()
        val pagingData = PagingData.from(testData)
        val flowable = Flowable.just(pagingData)

        Mockito.`when`(collectPagingDataUseCase.data(com.theblackbit.animemania.android.util.TypeOfData.ANIME, com.theblackbit.animemania.android.util.CategoryHolder.POPULAR))
            .thenReturn(flowable)

        sut.startToCollectData(com.theblackbit.animemania.android.util.TypeOfData.ANIME, com.theblackbit.animemania.android.util.CategoryHolder.POPULAR, testScope)

        Mockito.verify(collectPagingDataUseCase).data(com.theblackbit.animemania.android.util.TypeOfData.ANIME, com.theblackbit.animemania.android.util.CategoryHolder.POPULAR)
    }

    private fun createTestData(): List<com.theblackbit.animemania.android.model.Data> {
        return emptyList()
    }
}
