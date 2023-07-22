package com.theblackbit.animemania.android.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.util.CategoryHolder
import io.reactivex.rxjava3.core.Flowable

class MangaTabFragment : CollectionTabFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_tab_content

    override fun trendingWeekFlowable(): Flowable<PagingData<Collection>> {
        return viewModel.startToCollectMangaData(
            categoryHolder = CategoryHolder.WEEK_POPULAR,
            scope = viewModel.viewModelScope,
        )
    }

    override fun mostWantedFlowable(): Flowable<PagingData<Collection>> {
        return viewModel.startToCollectMangaData(
            categoryHolder = CategoryHolder.MOST_WANTED,
            scope = viewModel.viewModelScope,
        )
    }

    override fun topRatedFlowable(): Flowable<PagingData<Collection>> {
        return viewModel.startToCollectMangaData(
            categoryHolder = CategoryHolder.TOP_RATED,
            scope = viewModel.viewModelScope,
        )
    }

    override fun popularFlowable(): Flowable<PagingData<Collection>> {
        return viewModel.startToCollectMangaData(
            categoryHolder = CategoryHolder.POPULAR,
            scope = viewModel.viewModelScope,
        )
    }
}
