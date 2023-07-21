package com.theblackbit.animemania.android.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.theblackbit.animemania.android.domain.usecase.CollectPagingDataUseCase
import com.theblackbit.animemania.android.model.Data
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class HomeTabContentFragmentViewModel(
    private val collectPagingDataUseCase: CollectPagingDataUseCase,
) : ViewModel() {

    fun startToCollectData(
        typeOfData: com.theblackbit.animemania.android.util.TypeOfData,
        categoryHolder: com.theblackbit.animemania.android.util.CategoryHolder,
        scope: CoroutineScope,
    ): Flowable<PagingData<Data>> {
        return collectPagingDataUseCase.data(
            typeOfData = typeOfData,
            categoryHolder = categoryHolder,
        ).cachedIn(scope)
    }
}
