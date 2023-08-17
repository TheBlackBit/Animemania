package com.theblackbit.animemania.android.detail.pagertabs.chapter

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.theblackbit.animemania.android.domain.usecase.CollectChaptersUseCase
import com.theblackbit.animemania.android.model.Chapter
import com.theblackbit.animemania.android.model.CollectionType
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class ChapterTabViewModel(
    private val chaptersUseCase: CollectChaptersUseCase,
) : ViewModel() {
    fun startToCollectChapters(
        collectionId: String,
        collectionType: CollectionType,
        scope: CoroutineScope,
    ): Flowable<PagingData<Chapter>> {
        return chaptersUseCase.collect(collectionId, collectionType).cachedIn(scope)
    }
}
