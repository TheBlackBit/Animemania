package com.theblackbit.animemania.android.detail.pagertabs.character

import android.os.Bundle
import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.theblackbit.animemania.android.common.BundleKeys.COLLECTION_ID
import com.theblackbit.animemania.android.common.BundleKeys.COLLECTION_TYPE
import com.theblackbit.animemania.android.common.FragmentBindingCreator
import com.theblackbit.animemania.android.feature.detail.R
import com.theblackbit.animemania.android.feature.detail.databinding.FragmentTabCharactersBinding
import com.theblackbit.animemania.android.model.Character
import com.theblackbit.animemania.android.model.CollectionType
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersTabFragment : FragmentBindingCreator<FragmentTabCharactersBinding>() {

    private val viewModel: CharactersTabViewModel by viewModel()

    private val dataDisposable = CompositeDisposable()

    private val adapter: CharactersAdapter by lazy {
        initAdapter()
    }

    override val layoutId: Int
        get() = R.layout.fragment_tab_characters

    companion object {
        fun createFragment(
            collectionId: String,
            collectionType: CollectionType,
        ): CharactersTabFragment {
            val fragment = CharactersTabFragment()
            val bundle = Bundle()

            bundle.apply {
                putString(COLLECTION_ID, collectionId)
                putString(COLLECTION_TYPE, collectionType.collectionName)
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
            if (loadState.refresh is LoadState.NotLoading) {
                hideProgressBar()
            }
        }
    }

    private fun startToCollectChapters() {
        arguments?.apply {
            val collectionId = getString(COLLECTION_ID, "")
            val collectionType = getString(COLLECTION_TYPE, "")
            dataDisposable.add(
                viewModel.startToCollectCharacters(
                    collectionId = collectionId,
                    collectionType = collectionType,
                    scope = viewModel.viewModelScope,
                )
                    .subscribe({ characters ->
                        addElementToRecyclerView(characters)
                    }, { error ->
                        error.printStackTrace()
                    }),
            )
        }
    }

    private fun showNoDataView() {
        binding.showNoData = true
    }

    private fun initAdapter(): CharactersAdapter {
        return CharactersAdapter(CharacterDiffCallBack())
    }

    private fun initRecyclerView() {
        binding.rvCharacters.adapter = adapter
    }

    private fun hideProgressBar() {
        binding.hideProgress = true
    }

    private fun addElementToRecyclerView(characters: PagingData<Character>) {
        adapter.submitData(lifecycle, characters)
    }

    override fun onDestroyView() {
        dataDisposable.dispose()
        super.onDestroyView()
    }
}
