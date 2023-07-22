package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.util.CategoryHolder
import io.reactivex.rxjava3.core.Flowable

interface CollectCollectionDataUseCase {
    fun collectByCategory(
        categoryHolder: CategoryHolder,
    ): Flowable<PagingData<Collection>>
}
