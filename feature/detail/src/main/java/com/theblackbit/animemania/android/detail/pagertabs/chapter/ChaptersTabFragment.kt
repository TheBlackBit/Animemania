package com.theblackbit.animemania.android.detail.pagertabs.chapter

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.theblackbit.animemania.android.common.FragmentBindingCreator
import com.theblackbit.animemania.android.detail.DetailFragment.Companion.COLLECTION_ID
import com.theblackbit.animemania.android.feature.detail.R
import com.theblackbit.animemania.android.feature.detail.databinding.FragmentTabChaptersBinding
import com.theblackbit.animemania.android.model.Chapter
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
        ): ChaptersTabFragment {
            val fragment = ChaptersTabFragment()
            val bundle = Bundle()

            bundle.apply {
                putString(COLLECTION_ID, collectionId)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        startToCollectChapters()
    }

    private fun startToCollectChapters() {
        arguments?.apply {
            val collectionId = getString(COLLECTION_ID, "")
            dataDisposable.add(
                viewModel.startToCollectChapters(collectionId, viewModel.viewModelScope)
                    .subscribe({ chapters ->
                        hideProgressBar()
                        addElementToRecyclerView(chapters)
                    }, { error ->
                        error.printStackTrace()
                        // TODO: SHOW NO DATA
                    }),
            )
        }
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
