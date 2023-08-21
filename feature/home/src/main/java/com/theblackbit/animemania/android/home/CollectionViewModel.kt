package com.theblackbit.animemania.android.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class CollectionViewModel(
    private val collectAnimeDataUseCase: CollectCollectionDataUseCase,
    private val collectMangaDataUseCase: CollectCollectionDataUseCase
) : ViewModel() {

    var initDataCollected = false

    fun startToCollectAnimeData(
        requestType: RequestType,
        scope: CoroutineScope
    ): Flowable<PagingData<Collection>> =
        collectAnimeDataUseCase.collectByCategory(
            requestType = requestType
        ).cachedIn(scope)

    fun startToCollectMangaData(
        requestType: RequestType,
        scope: CoroutineScope
    ): Flowable<PagingData<Collection>> =
        collectMangaDataUseCase.collectByCategory(
            requestType = requestType
        ).cachedIn(scope)
}
