package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.model.Character
import io.reactivex.rxjava3.core.Flowable

interface CollectCharactersUseCase {
    fun collect(collectionId: String, mediaType: String): Flowable<PagingData<Character>>
}
