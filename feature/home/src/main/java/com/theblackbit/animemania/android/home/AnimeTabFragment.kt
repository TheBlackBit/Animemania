package com.theblackbit.animemania.android.home

import androidx.lifecycle.viewModelScope
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.home.adapter.DataAdapter
import com.theblackbit.animemania.android.home.adapter.DataDiffCallback
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AnimeTabFragment : CollectionTabFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_tab_content

    override fun collectCategories(): Disposable {
        return viewModel.startToCollectAnimeCategories()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .flatMap { categories ->
                // MOVE TO A METHOD
                Flowable.fromIterable(categories)
                    .flatMap { category ->
                        viewModel.startToCollectAnimeData(
                            category.categoryId,
                            viewModel.viewModelScope,
                        ).map { pagingData ->
                            Pair(category, pagingData)
                        }
                    }
                    .singleOrError()
            }.subscribe { pairOfData ->
                // MOVE TO A METHOD
                val indexOfAdapter =
                    dataForAdapter.indexOfFirst { dataAdapter -> dataAdapter.second == pairOfData.first.categoryName }

                if (indexOfAdapter == -1) {
                    dataForAdapter.add(
                        Pair(
                            DataAdapter(DataDiffCallback()),
                            pairOfData.first.categoryName,
                        ),
                    )
                }

                if (indexOfAdapter > -1) {
                    dataForAdapter[indexOfAdapter].first.submitData(lifecycle, pairOfData.second)
                }
            }
    }
}
