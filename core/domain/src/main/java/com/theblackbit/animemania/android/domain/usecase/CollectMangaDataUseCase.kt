package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.theblackbit.animemania.android.data.pagingsource.collection.CollectionPagingSource
import com.theblackbit.animemania.android.data.pagingsource.collection.manga.MangaPagingSourceFactory
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Flowable

// TODO: ADD UNIT TEST
class CollectMangaDataUseCase(
    private val pagingSourceFactory: MangaPagingSourceFactory,
) : CollectCollectionDataUseCase {
    override fun collectByCategory(categoryId: Int): Flowable<PagingData<Collection>> {
        return Pager(
            config = PagingConfig(
                pageSize = CollectionPagingSource.COLLECTION_PAGE_LIMIT,
            ),
            pagingSourceFactory = {
                pagingSourceFactory.getMangaPagingSource(categoryId)
            },
        ).flowable
    }
}
