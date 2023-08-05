package com.theblackbit.animemania.android.detail.pagertabs.chapter

import androidx.paging.PagingData
import com.theblackbit.animemania.android.domain.usecase.CollectChaptersUseCase
import com.theblackbit.animemania.android.model.Chapter
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ChapterTabViewModelTest {

    private lateinit var chapterTabViewModel: ChapterTabViewModel

    @Mock
    lateinit var chaptersUseCase: CollectChaptersUseCase

    private val testScope = TestScope()

    @Before
    fun setUp() {
        chapterTabViewModel = ChapterTabViewModel(chaptersUseCase)
    }

    @Test
    fun testCollectChapters() {
        val pagingData: Flowable<PagingData<Chapter>> = Flowable.just(PagingData.from(emptyList()))
        Mockito.`when`(chaptersUseCase.collect("1"))
            .thenReturn(pagingData)

        chapterTabViewModel.startToCollectChapters("1", testScope)

        Mockito.verify(chaptersUseCase).collect("1")
    }
}
