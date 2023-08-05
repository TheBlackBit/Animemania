package com.theblackbit.animemania.android.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.model.Category
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class CollectionViewModel(
    private val collectAnimeDataUseCase: CollectCollectionDataUseCase,
    private val collectMangaDataUseCase: CollectCollectionDataUseCase,
    private val collectAnimeCategoriesUseCase: CollectCategoriesUseCase,
    private val collectMangaCategoriesUseCase: CollectCategoriesUseCase,
) : ViewModel() {

    var initDataCollected = false

    fun startToCollectAnimeCategories(): Single<List<Category>> =
        collectAnimeCategoriesUseCase.collect()

    fun startToCollectMangaCategories(): Single<List<Category>> =
        collectMangaCategoriesUseCase.collect()

    fun startToCollectAnimeData(
        categoryId: Int,
        scope: CoroutineScope,
    ): Flowable<PagingData<Collection>> =
        collectAnimeDataUseCase.collectByCategory(
            categoryId = categoryId,
        ).cachedIn(scope)

    fun startToCollectMangaData(
        categoryId: Int,
        scope: CoroutineScope,
    ): Flowable<PagingData<Collection>> =
        collectMangaDataUseCase.collectByCategory(
            categoryId = categoryId,
        ).cachedIn(scope)
}
