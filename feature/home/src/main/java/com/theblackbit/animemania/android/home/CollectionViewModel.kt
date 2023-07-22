package com.theblackbit.animemania.android.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.util.CategoryHolder
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class CollectionViewModel(
    private val collectAnimeDataUseCase: CollectCollectionDataUseCase,
    private val collectMangaDataUseCase: CollectCollectionDataUseCase,
) : ViewModel() {

    fun startToCollectAnimeData(
        categoryHolder: CategoryHolder,
        scope: CoroutineScope,
    ): Flowable<PagingData<Collection>> {
        return collectAnimeDataUseCase.collectByCategory(
            categoryHolder = categoryHolder,
        ).cachedIn(scope)
    }

    fun startToCollectMangaData(
        categoryHolder: CategoryHolder,
        scope: CoroutineScope,
    ): Flowable<PagingData<Collection>> {
        return collectMangaDataUseCase.collectByCategory(
            categoryHolder = categoryHolder,
        ).cachedIn(scope)
    }
}
