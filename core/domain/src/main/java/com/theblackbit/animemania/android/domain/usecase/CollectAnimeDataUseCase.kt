package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.pagingsource.collection.anime.AnimePagingSourceFactory
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Flowable

class CollectAnimeDataUseCase(
    private val pagingSourceFactory: AnimePagingSourceFactory,
    private val pagingConfig: PagingConfig
) : CollectCollectionDataUseCase {
    override fun collectByCategory(
        requestType: RequestType

    ): Flowable<PagingData<Collection>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                pagingSourceFactory.getAnimePagingSource(requestType)
            }
        ).flowable
    }
}
