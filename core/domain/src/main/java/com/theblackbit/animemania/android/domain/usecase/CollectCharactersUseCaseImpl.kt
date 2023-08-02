package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.model.Character
import io.reactivex.rxjava3.core.Flowable

class CollectCharactersUseCaseImpl : CollectCharactersUseCase {
    override fun collect(collectionId: String): Flowable<PagingData<Character>> {
        return Flowable.just(PagingData.from(emptyList()))
    }
}
