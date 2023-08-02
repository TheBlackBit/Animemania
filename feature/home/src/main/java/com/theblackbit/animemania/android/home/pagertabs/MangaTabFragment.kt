package com.theblackbit.animemania.android.home.pagertabs

import androidx.lifecycle.viewModelScope
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.model.Category

class MangaTabFragment : CollectionTabFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_tab_content

    override fun fetchCollectionsByCategory() {
        dataDisposable.add(
            viewModel.startToCollectMangaCategories()
                .subscribe({ categories ->
                    populateRecyclerView(categories)
                    collectMangaCollection(categories)
                }, { error -> error.printStackTrace() }),
        )
    }

    private fun collectMangaCollection(categories: List<Category>) {
        categories.forEachIndexed { index, category ->
            dataDisposable.add(
                viewModel.startToCollectMangaData(
                    category.categoryId,
                    viewModel.viewModelScope,
                )
                    .subscribe(
                        { data ->
                            notifyInitDataCollected(index, categories)
                            submitDataIntoAdapter(index, data)
                        },
                        // TODO: SHOW NO DATA
                        { error -> error.printStackTrace() },
                    ),
            )
        }
    }
}
