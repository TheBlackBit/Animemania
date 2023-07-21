package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.model.Data
import com.theblackbit.animemania.android.util.CategoryHolder
import com.theblackbit.animemania.android.util.TypeOfData
import io.reactivex.rxjava3.core.Flowable

class CollectPagingDataUseCaseImpl : CollectPagingDataUseCase {
    override fun data(
        typeOfData: TypeOfData,
        categoryHolder: CategoryHolder,
    ): Flowable<PagingData<Data>> {
        return Flowable.just(PagingData.from(emptyList()))
    }
}
