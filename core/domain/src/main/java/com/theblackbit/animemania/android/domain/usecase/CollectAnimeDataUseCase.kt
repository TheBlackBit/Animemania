package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.pagingsource.collection.CollectionPagingSource
import com.theblackbit.animemania.android.data.pagingsource.collection.anime.AnimePagingSourceFactory
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Flowable

class CollectAnimeDataUseCase(
    private val pagingSourceFactory: AnimePagingSourceFactory,
) : CollectCollectionDataUseCase {
    override fun collectByCategory(requestType: RequestType): Flowable<PagingData<Collection>> {
        return Pager(
            config = PagingConfig(
                pageSize = CollectionPagingSource.COLLECTION_PAGE_LIMIT,
                prefetchDistance = CollectionPagingSource.COLLECTION_PAGE_LIMIT / 2,
            ),
            pagingSourceFactory = {
                pagingSourceFactory.getAnimePagingSource(requestType)
            },
        ).flowable
    }
}
