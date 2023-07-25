package com.theblackbit.animemania.android.home

import android.os.Bundle
import android.view.View
import com.theblackbit.animemania.android.common.FragmentBindingCreator
import com.theblackbit.animemania.android.feature.home.databinding.FragmentTabContentBinding
import com.theblackbit.animemania.android.home.adapter.DataAdapter
import com.theblackbit.animemania.android.home.adapter.DataContainerAdapter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class CollectionTabFragment : FragmentBindingCreator<FragmentTabContentBinding>() {

    protected val viewModel: CollectionViewModel by viewModel()

    private val dataDisposable = CompositeDisposable()

    protected val dataForAdapter: ArrayList<Pair<DataAdapter, String>> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvData.adapter = DataContainerAdapter(dataForAdapter)
        dataDisposable.add(collectCategories())
    }

    abstract fun collectCategories(): Disposable

    override fun onDestroyView() {
        dataDisposable.dispose()
        super.onDestroyView()
    }
}
