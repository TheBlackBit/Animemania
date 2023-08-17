package com.theblackbit.animemania.android.detail.pagertabs.character

import androidx.paging.PagingData
import com.theblackbit.animemania.android.domain.usecase.CollectCharactersUseCase
import com.theblackbit.animemania.android.model.Character
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
class CharacterTabViewModelTest {

    private lateinit var charactersTabViewModel: CharactersTabViewModel

    @Mock
    lateinit var charactersUseCase: CollectCharactersUseCase

    private val testScope = TestScope()

    @Before
    fun setUp() {
        charactersTabViewModel = CharactersTabViewModel(charactersUseCase)
    }

    @Test
    fun testCollectCharacters() {
        val pagingData: Flowable<PagingData<Character>> =
            Flowable.just(PagingData.from(emptyList()))
        Mockito.`when`(charactersUseCase.collect("1", "Anime"))
            .thenReturn(pagingData)

        charactersTabViewModel.startToCollectCharacters("1", "Anime", testScope)

        Mockito.verify(charactersUseCase).collect("1", "Anime")
    }
}
