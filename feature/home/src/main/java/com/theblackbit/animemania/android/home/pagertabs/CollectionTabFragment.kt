package com.theblackbit.animemania.android.home.pagertabs

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.theblackbit.animemania.android.common.BundleKeys.COLLECTION_ID
import com.theblackbit.animemania.android.common.BundleKeys.COLLECTION_TYPE
import com.theblackbit.animemania.android.common.BundleKeys.COVER_IMAGE
import com.theblackbit.animemania.android.common.BundleKeys.END_DATE
import com.theblackbit.animemania.android.common.BundleKeys.GENRES
import com.theblackbit.animemania.android.common.BundleKeys.POSTER_IMAGE
import com.theblackbit.animemania.android.common.BundleKeys.RATING
import com.theblackbit.animemania.android.common.BundleKeys.START_DATE
import com.theblackbit.animemania.android.common.BundleKeys.STATE
import com.theblackbit.animemania.android.common.BundleKeys.SYNOPSIS
import com.theblackbit.animemania.android.common.BundleKeys.TITLE
import com.theblackbit.animemania.android.common.BundleKeys.TRANSITION_NAME
import com.theblackbit.animemania.android.common.FragmentBindingCreator
import com.theblackbit.animemania.android.core.resources.R
import com.theblackbit.animemania.android.feature.home.databinding.FragmentTabContentBinding
import com.theblackbit.animemania.android.home.CollectionViewModel
import com.theblackbit.animemania.android.home.adapter.DataAdapter
import com.theblackbit.animemania.android.home.adapter.DataContainerAdapter
import com.theblackbit.animemania.android.home.adapter.DataDiffCallback
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.model.CollectionType
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class CollectionTabFragment(
    private val collectionType: CollectionType
) : FragmentBindingCreator<FragmentTabContentBinding>() {

    protected val dataDisposable = CompositeDisposable()

    protected val viewModel: CollectionViewModel by viewModel()

    private val dataForAdapter: ArrayList<Pair<DataAdapter, String>> = ArrayList()

    abstract fun fetchCollectionsByRequestType()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val initDataCollected = viewModel.initDataCollected
        binding.initDataCollected = initDataCollected
        if (!initDataCollected) {
            fetchCollectionsByRequestType()
            binding.rvData.adapter = DataContainerAdapter(dataForAdapter)
        }
    }

    protected fun addRecyclerView(requestTypeName: String) {
        val dataAdapter = DataAdapter(
            diffCallback = DataDiffCallback(),
            onClickCollection = object : DataAdapter.OnClickCollection {
                override fun onClick(collection: Collection, imageView: ImageView) {
                    navigateToDetail(collection, imageView, requestTypeName)
                }
            },
            requestTypeName = requestTypeName
        )

        dataForAdapter.add(
            Pair(
                dataAdapter,
                requestTypeName
            )
        )

        dataForAdapter.last().first.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.NotLoading) {
                hideProgress()
            }
        }
    }

    private fun navigateToDetail(
        collection: Collection,
        imageView: ImageView,
        requestTypeName: String
    ) {
        val transitionName = collection.collectionId.plus(requestTypeName)
        val bundle = getBundleOfProperties(collection, transitionName = transitionName)
        val extras = FragmentNavigatorExtras(
            imageView to transitionName
        )
        findNavController().navigate(
            resId = R.id.action_homeFragment_to_detailFragment,
            args = bundle,
            navOptions = null,
            navigatorExtras = extras
        )
    }

    private fun getBundleOfProperties(collection: Collection, transitionName: String): Bundle {
        val bundle = Bundle()
        bundle
            .apply {
                putString(TRANSITION_NAME, transitionName)
                putString(COLLECTION_ID, collection.collectionId)
                putString(TITLE, collection.name)
                putString(COVER_IMAGE, collection.bigPosterImageUrl)
                putString(POSTER_IMAGE, collection.miniPosterImageUrl)
                putString(RATING, collection.averageRating)
                putString(STATE, collection.status)
                putString(START_DATE, collection.startDate)
                putString(END_DATE, collection.endDate)
                putString(
                    GENRES,
                    collection.genre.joinToString(separator = " \u25CF ") { it.name }
                )
                putString(SYNOPSIS, collection.synopsis)
                putString(COLLECTION_TYPE, collectionType.name)
            }

        return bundle
    }

    protected fun submitDataIntoAdapter(
        requestTypeName: String,
        pagingData: PagingData<Collection>
    ) {
        dataForAdapter.find { it.second == requestTypeName }?.first
            ?.submitData(lifecycle, pagingData)
    }

    private fun hideProgress() {
        binding.initDataCollected = true
        viewModel.initDataCollected = true
    }

    override fun onDestroyView() {
        dataDisposable.dispose()
        super.onDestroyView()
    }
}
