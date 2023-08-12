package com.theblackbit.animemania.android.detail.pagertabs.chapter

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.theblackbit.animemania.android.common.BundleKeys.COLLECTION_ID
import com.theblackbit.animemania.android.common.BundleKeys.COLLECTION_TYPE
import com.theblackbit.animemania.android.common.FragmentBindingCreator
import com.theblackbit.animemania.android.feature.detail.R
import com.theblackbit.animemania.android.feature.detail.databinding.FragmentTabChaptersBinding
import com.theblackbit.animemania.android.model.Chapter
import com.theblackbit.animemania.android.model.CollectionType
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChaptersTabFragment : FragmentBindingCreator<FragmentTabChaptersBinding>() {

    private val viewModel: ChapterTabViewModel by viewModel()

    private val dataDisposable = CompositeDisposable()

    private val adapter: ChaptersAdapter by lazy {
        initAdapter()
    }

    override val layoutId: Int
        get() = R.layout.fragment_tab_chapters

    companion object {
        fun createFragment(
            collectionId: String,
            collectionType: CollectionType,
        ): ChaptersTabFragment {
            val fragment = ChaptersTabFragment()
            val bundle = Bundle()

            bundle.apply {
                putString(COLLECTION_ID, collectionId)
                putString(COLLECTION_TYPE, collectionType.name)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        startToCollectChapters()
        adapter.addLoadStateListener { loadState ->
            if (loadState.append.endOfPaginationReached) {
                if (adapter.itemCount < 1) {
                    showNoDataView()
                }
            }
        }
    }

    private fun startToCollectChapters() {
        arguments?.apply {
            val collectionId = getString(COLLECTION_ID, "")
            // TODO: FIX THIS
            val collectionType = getString(COLLECTION_TYPE, "")
            dataDisposable.add(
                viewModel.startToCollectChapters(
                    collectionId,
                    getCollectionTypeByName(collectionType),
                    viewModel.viewModelScope,
                )
                    .subscribe({ chapters ->
                        addElementToRecyclerView(chapters)
                        hideProgressBar()
                    }, { error ->
                        error.printStackTrace()
                    }),
            )
        }
    }

    private fun getCollectionTypeByName(collectionTypeName: String): CollectionType {
        return when (collectionTypeName) {
            CollectionType.MANGA.name -> CollectionType.MANGA
            else -> CollectionType.ANIME
        }
    }

    private fun showNoDataView() {
        binding.showNoData = true
    }

    private fun initAdapter(): ChaptersAdapter {
        return ChaptersAdapter(ChapterDiffCallback())
    }

    private fun initRecyclerView() {
        binding.rvChapters.adapter = adapter
    }

    private fun hideProgressBar() {
        binding.hideProgress = true
    }

    private fun addElementToRecyclerView(chapters: PagingData<Chapter>) {
        adapter.submitData(lifecycle, chapters)
    }

    override fun onDestroyView() {
        dataDisposable.dispose()
        super.onDestroyView()
    }
}
