package com.theblackbit.animemania.android.home.pagertabs

import androidx.lifecycle.viewModelScope
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.model.Category

class AnimeTabFragment : CollectionTabFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_tab_content

    override fun fetchCollectionsByCategory() {
        dataDisposable.add(
            viewModel.startToCollectAnimeCategories()
                .subscribe({ categories ->
                    populateRecyclerView(categories)
                    collectAnimeCollection(categories)
                }, { error -> error.printStackTrace() }),
        )
    }

    private fun collectAnimeCollection(categories: List<Category>) {
        categories.forEachIndexed { index, category ->
            dataDisposable.add(
                viewModel.startToCollectAnimeData(
                    category.categoryId,
                    viewModel.viewModelScope,
                )
                    .subscribe(
                        { data ->
                            notifyInitDataCollected(index, categories)
                            submitDataIntoAdapter(index, data)
                        },
                        // TODO: SHOW NO DATA AND TEST
                        { error ->
                            error.printStackTrace()
                        },
                    ),
            )
        }
    }
}
