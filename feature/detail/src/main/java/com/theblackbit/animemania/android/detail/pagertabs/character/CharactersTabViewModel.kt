package com.theblackbit.animemania.android.detail.pagertabs.character

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.theblackbit.animemania.android.domain.usecase.CollectCharactersUseCase
import com.theblackbit.animemania.android.model.Character
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersTabViewModel(
    private val charactersUseCase: CollectCharactersUseCase
) : ViewModel() {
    fun startToCollectCharacters(
        collectionId: String,
        collectionType: String,
        scope: CoroutineScope
    ): Flowable<PagingData<Character>> {
        return charactersUseCase.collect(collectionId, collectionType)
            .cachedIn(scope)
    }
}
