package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.theblackbit.animemania.android.data.pagingsource.chapter.ChapterPagingSourceFactory
import com.theblackbit.animemania.android.model.Chapter
import com.theblackbit.animemania.android.model.CollectionType
import io.reactivex.rxjava3.core.Flowable

class CollectChaptersUseCaseImpl(
    private val chapterPagingSourceFactory: ChapterPagingSourceFactory,
    private val pagingConfig: PagingConfig
) : CollectChaptersUseCase {
    override fun collect(
        collectionId: String,
        collectionType: CollectionType
    ): Flowable<PagingData<Chapter>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                chapterPagingSourceFactory.getChapterPagingSourceCollectionId(
                    collectionId = collectionId,
                    collectionType = collectionType
                )
            }
        ).flowable
    }
}
