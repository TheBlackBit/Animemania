package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Flowable

class CollectAnimeDataUseCase : CollectCollectionDataUseCase {
    override fun collectByCategory(categoryId: Int): Flowable<PagingData<Collection>> {
        return Flowable.just(PagingData.from(emptyList()))
    }
}
