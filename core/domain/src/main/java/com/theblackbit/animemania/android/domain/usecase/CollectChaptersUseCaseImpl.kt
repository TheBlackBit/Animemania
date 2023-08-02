package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.model.Chapter
import io.reactivex.rxjava3.core.Flowable

class CollectChaptersUseCaseImpl : CollectChaptersUseCase {
    override fun collect(collectionId: String): Flowable<PagingData<Chapter>> {
        return Flowable.just(PagingData.from(emptyList()))
    }
}
