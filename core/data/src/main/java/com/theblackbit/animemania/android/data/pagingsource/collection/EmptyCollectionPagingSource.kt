package com.theblackbit.animemania.android.data.pagingsource.collection

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Single

class EmptyCollectionPagingSource : RxPagingSource<Int, Collection>() {
    override fun getRefreshKey(state: PagingState<Int, Collection>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Collection>> {
        return Single.just(LoadResult.Error(Exception()))
    }
}
