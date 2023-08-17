package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.model.Chapter
import com.theblackbit.animemania.android.model.CollectionType
import io.reactivex.rxjava3.core.Flowable

interface CollectChaptersUseCase {
    fun collect(collectionId: String, collectionType: CollectionType): Flowable<PagingData<Chapter>>
}
