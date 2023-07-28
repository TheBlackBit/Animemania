package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Flowable

interface CollectCollectionDataUseCase {
    fun collectByCategory(
        categoryId: Int,
    ): Flowable<PagingData<Collection>>
}
