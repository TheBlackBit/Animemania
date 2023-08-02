package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.model.Chapter
import io.reactivex.rxjava3.core.Flowable

interface CollectChaptersUseCase {
    fun collect(collectionId: String): Flowable<PagingData<Chapter>>
}
