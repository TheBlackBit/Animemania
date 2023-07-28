package com.theblackbit.animemania.android.home.pagertabs

import android.os.Bundle
import android.view.View
import androidx.paging.PagingData
import com.theblackbit.animemania.android.common.FragmentBindingCreator
import com.theblackbit.animemania.android.feature.home.databinding.FragmentTabContentBinding
import com.theblackbit.animemania.android.home.CollectionViewModel
import com.theblackbit.animemania.android.home.adapter.DataAdapter
import com.theblackbit.animemania.android.home.adapter.DataContainerAdapter
import com.theblackbit.animemania.android.home.adapter.DataDiffCallback
import com.theblackbit.animemania.android.model.Category
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class CollectionTabFragment : FragmentBindingCreator<FragmentTabContentBinding>() {

    protected val dataDisposable = CompositeDisposable()

    protected val viewModel: CollectionViewModel by viewModel()

    private val dataForAdapter: ArrayList<Pair<DataAdapter, String>> = ArrayList()

    abstract fun fetchCollectionsByCategory()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvData.adapter = DataContainerAdapter(dataForAdapter)
        fetchCollectionsByCategory()
    }

    protected fun populateRecyclerView(categories: List<Category>) {
        categories.forEach { category ->
            dataForAdapter.add(
                Pair(
                    DataAdapter(DataDiffCallback()),
                    category.categoryName,
                ),
            )
        }
    }

    protected fun notifyInitDataCollected(index: Int, categories: List<Category>) {
        if (index == categories.lastIndex) binding.initDataCollected = true
    }

    protected fun submitDataIntoAdapter(index: Int, pagingData: PagingData<Collection>) {
        dataForAdapter[index].first.submitData(lifecycle, pagingData)
    }

    override fun onDestroyView() {
        dataDisposable.dispose()
        super.onDestroyView()
    }
}
