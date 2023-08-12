package com.theblackbit.animemania.android.home.pagertabs

import androidx.lifecycle.viewModelScope
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.model.Category
import com.theblackbit.animemania.android.model.CollectionType

class AnimeTabFragment : CollectionTabFragment(CollectionType.ANIME) {

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
                            submitDataIntoAdapter(index, data)
                        },
                        { error ->
                            error.printStackTrace()
                        },
                    ),
            )
        }
    }
}
