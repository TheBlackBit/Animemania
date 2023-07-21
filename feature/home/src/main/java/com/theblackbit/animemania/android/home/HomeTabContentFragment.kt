package com.theblackbit.animemania.android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.theblackbit.animemania.android.feature.home.databinding.FragmentTabContentBinding
import com.theblackbit.animemania.android.home.adapter.DataAdapter
import com.theblackbit.animemania.android.home.adapter.DataContainerAdapter
import com.theblackbit.animemania.android.home.adapter.DataDiffCallback
import com.theblackbit.animemania.android.model.Data
import com.theblackbit.animemania.android.resources.R
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeTabContentFragment : Fragment() {

    private lateinit var binding: FragmentTabContentBinding

    private var codeOfTypeOfData: Int? = null

    private lateinit var trendingWeekFlowable: Flowable<PagingData<com.theblackbit.animemania.android.model.Data>>

    private lateinit var mostWantedFlowable: Flowable<PagingData<com.theblackbit.animemania.android.model.Data>>

    private lateinit var topRatedFlowable: Flowable<PagingData<com.theblackbit.animemania.android.model.Data>>

    private lateinit var popularFlowable: Flowable<PagingData<com.theblackbit.animemania.android.model.Data>>

    private val dataDisposable = CompositeDisposable()

    private val viewModel: HomeTabContentFragmentViewModel by viewModel()

    private val dataForAdapter: List<Pair<DataAdapter, String>> by lazy {
        createDataForAdapter()
    }

    companion object {
        const val TAB_CODE: String = "tab_code"

        fun newInstance(typeOfData: com.theblackbit.animemania.android.util.TypeOfData) =
            HomeTabContentFragment().apply {
                arguments = Bundle().apply {
                    putInt(TAB_CODE, typeOfData.code)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            codeOfTypeOfData = it.getInt(TAB_CODE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTabContentBinding.inflate(inflater, container, false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvData.adapter = DataContainerAdapter(dataForAdapter)
        val typeOfData =
            if (com.theblackbit.animemania.android.util.TypeOfData.ANIME.code == codeOfTypeOfData) com.theblackbit.animemania.android.util.TypeOfData.ANIME else com.theblackbit.animemania.android.util.TypeOfData.MANGA
        initFlowable(typeOfData)
        observeData()
    }

    private fun createDataForAdapter(): List<Pair<DataAdapter, String>> {
        return listOf(
            Pair(DataAdapter(DataDiffCallback()), getString(R.string.trending_week)),
            Pair(DataAdapter(DataDiffCallback()), getString(R.string.most_anticipated)),
            Pair(DataAdapter(DataDiffCallback()), getString(R.string.top_rated)),
            Pair(DataAdapter(DataDiffCallback()), getString(R.string.most_popular)),
        )
    }

    private fun observeData() {
        dataDisposable.add(
            Flowable.merge(trendingWeekFlowable, mostWantedFlowable)
                .toList()
                .subscribe { listOfResult ->
                    // TODO: Hide progress bar
                    listOfResult.forEachIndexed { index, pagingData ->
                        dataForAdapter[index].first.submitData(lifecycle, pagingData)
                    }
                },
        )
    }

    private fun initFlowable(typeOfData: com.theblackbit.animemania.android.util.TypeOfData) {
        trendingWeekFlowable = viewModel.startToCollectData(
            categoryHolder = com.theblackbit.animemania.android.util.CategoryHolder.WEEK_POPULAR,
            typeOfData = typeOfData,
            scope = viewModel.viewModelScope,
        )

        mostWantedFlowable = viewModel.startToCollectData(
            categoryHolder = com.theblackbit.animemania.android.util.CategoryHolder.MOST_WANTED,
            typeOfData = typeOfData,
            scope = viewModel.viewModelScope,
        )

        topRatedFlowable = viewModel.startToCollectData(
            categoryHolder = com.theblackbit.animemania.android.util.CategoryHolder.TOP_RATED,
            typeOfData = typeOfData,
            scope = viewModel.viewModelScope,
        )

        popularFlowable = viewModel.startToCollectData(
            categoryHolder = com.theblackbit.animemania.android.util.CategoryHolder.POPULAR,
            typeOfData = typeOfData,
            scope = viewModel.viewModelScope,
        )
    }

    override fun onDestroyView() {
        dataDisposable.dispose()
        super.onDestroyView()
    }
}
