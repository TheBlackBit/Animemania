package com.theblackbit.animemania.android.home.pagertabs

import androidx.lifecycle.viewModelScope
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.model.CollectionType
import com.theblackbit.animemania.android.core.resources.R as resourcesR

class AnimeTabFragment : CollectionTabFragment(CollectionType.ANIME) {

    override val layoutId: Int
        get() = R.layout.fragment_tab_content

    override fun fetchCollectionsByRequestType() {
        collectAndFetchRecyclerView(
            RequestType.TRENDING_ANIME,
            getString(resourcesR.string.trending_week),
        )
        collectAndFetchRecyclerView(
            RequestType.MOST_ANTICIPATED_ANIME,
            getString(resourcesR.string.most_anticipated),
        )
        collectAndFetchRecyclerView(
            RequestType.TOP_RATED_ANIME,
            getString(resourcesR.string.top_rated),
        )
        collectAndFetchRecyclerView(
            RequestType.POPULAR_ANIME,
            getString(resourcesR.string.most_popular),
        )
    }

    private fun collectAndFetchRecyclerView(requestType: RequestType, labelRecyclerView: String) {
        addRecyclerView(labelRecyclerView)
        dataDisposable.add(
            viewModel.startToCollectAnimeData(requestType, viewModel.viewModelScope)
                .subscribe({ categories ->
                    submitDataIntoAdapter(labelRecyclerView, categories)
                }, { error -> error.printStackTrace() }),
        )
    }
}
