package com.theblackbit.animemania.android.home.pagertabs

import androidx.lifecycle.viewModelScope
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.model.CollectionType

class MangaTabFragment : CollectionTabFragment(CollectionType.MANGA) {

    override val layoutId: Int
        get() = R.layout.fragment_tab_content

    override fun fetchCollectionsByRequestType() {
        collectAndFetchRecyclerView(
            RequestType.TRENDING_MANGA,
            getString(com.theblackbit.animemania.android.core.resources.R.string.trending_week),
        )
        collectAndFetchRecyclerView(
            RequestType.MOST_ANTICIPATED_MANGA,
            getString(com.theblackbit.animemania.android.core.resources.R.string.most_anticipated),
        )
        collectAndFetchRecyclerView(
            RequestType.TOP_RATED_MANGA,
            getString(com.theblackbit.animemania.android.core.resources.R.string.top_rated),
        )
        collectAndFetchRecyclerView(
            RequestType.POPULAR_MANGA,
            getString(com.theblackbit.animemania.android.core.resources.R.string.most_popular),
        )
    }

    private fun collectAndFetchRecyclerView(requestType: RequestType, labelRecyclerView: String) {
        addRecyclerView(labelRecyclerView)
        dataDisposable.add(
            viewModel.startToCollectMangaData(requestType, viewModel.viewModelScope)
                .subscribe({ categories ->
                    submitDataIntoAdapter(labelRecyclerView, categories)
                }, { error -> error.printStackTrace() }),
        )
    }
}
