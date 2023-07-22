package com.theblackbit.animemania.android.home

import android.os.Bundle
import android.view.View
import androidx.paging.PagingData
import com.theblackbit.animemania.android.common.FragmentBindingCreator
import com.theblackbit.animemania.android.feature.home.databinding.FragmentTabContentBinding
import com.theblackbit.animemania.android.home.adapter.DataAdapter
import com.theblackbit.animemania.android.home.adapter.DataContainerAdapter
import com.theblackbit.animemania.android.home.adapter.DataDiffCallback
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.resources.R
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class CollectionTabFragment : FragmentBindingCreator<FragmentTabContentBinding>() {

    protected val viewModel: CollectionViewModel by viewModel()

    private val dataDisposable = CompositeDisposable()

    private val dataForAdapter: List<Pair<DataAdapter, String>> by lazy {
        createDataForAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvData.adapter = DataContainerAdapter(dataForAdapter)
        observeData()
    }

    abstract fun trendingWeekFlowable(): Flowable<PagingData<Collection>>

    abstract fun mostWantedFlowable(): Flowable<PagingData<Collection>>

    abstract fun topRatedFlowable(): Flowable<PagingData<Collection>>

    abstract fun popularFlowable(): Flowable<PagingData<Collection>>

    private fun observeData() {
        dataDisposable.add(
            Flowable.merge(
                trendingWeekFlowable(),
                mostWantedFlowable(),
                topRatedFlowable(),
                popularFlowable(),
            )
                .toList()
                .subscribe { listOfResult ->
                    // TODO: Hide progress bar
                    listOfResult.forEachIndexed { index, pagingData ->
                        dataForAdapter[index].first.submitData(lifecycle, pagingData)
                    }
                },
        )
    }

    private fun createDataForAdapter(): List<Pair<DataAdapter, String>> {
        return listOf(
            Pair(DataAdapter(DataDiffCallback()), getString(R.string.trending_week)),
            Pair(DataAdapter(DataDiffCallback()), getString(R.string.most_anticipated)),
            Pair(DataAdapter(DataDiffCallback()), getString(R.string.top_rated)),
            Pair(DataAdapter(DataDiffCallback()), getString(R.string.most_popular)),
        )
    }

    override fun onDestroyView() {
        dataDisposable.dispose()
        super.onDestroyView()
    }
}
