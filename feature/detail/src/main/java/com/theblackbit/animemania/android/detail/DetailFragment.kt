package com.theblackbit.animemania.android.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
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
import com.theblackbit.animemania.android.common.OnBackNavigation
import com.theblackbit.animemania.android.detail.adapter.DetailTabAdapter
import com.theblackbit.animemania.android.detail.pagertabs.OverviewTabFragment
import com.theblackbit.animemania.android.detail.pagertabs.chapter.ChaptersTabFragment
import com.theblackbit.animemania.android.detail.pagertabs.character.CharactersTabFragment
import com.theblackbit.animemania.android.feature.detail.databinding.FragmentDetailBinding
import com.theblackbit.animemania.android.model.CollectionType
import com.theblackbit.animemania.android.core.resources.R as resourcesR

class DetailFragment : Fragment(), OnBackNavigation {

    private lateinit var binding: FragmentDetailBinding

    private lateinit var detailTabAdapter: DetailTabAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::binding.isInitialized) {
            binding = FragmentDetailBinding.inflate(inflater, container, false)
        }
        binding.also {
            it.lifecycleOwner = viewLifecycleOwner
        }

        configAnimationTransition()
        configBindingProperties()
        configViewPagerWithTabs()
        configTabLayout()

        return binding.root
    }

    private fun configAnimationTransition() {
        val animation =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
        arguments?.apply {
            val transitionName = getString(TRANSITION_NAME, "")
            binding.apply {
                imageCover.transitionName = transitionName
            }
        }
    }

    private fun configBindingProperties() {
        arguments?.apply {
            val coverImageVal = getString(COVER_IMAGE, "")
            val posterImageVal = getString(POSTER_IMAGE, "")
            val titleVal = getString(TITLE, "")
            val ratingVal = getString(RATING, "")

            binding.apply {
                coverImage = coverImageVal
                posterImage = posterImageVal
                title = titleVal
                rating = ratingVal
                bacKNavigation = this@DetailFragment
            }
        }
    }

    private fun configViewPagerWithTabs() {
        arguments?.apply {
            val collectionId = getString(COLLECTION_ID, "")
            val state = getString(STATE, "")
            val startDate = getString(START_DATE, "")
            val endDate = getString(END_DATE, "")
            val genres = getString(GENRES, "")
            val synopsis = getString(SYNOPSIS, "")
            val collectionType = getString(COLLECTION_TYPE, "")
            val validCollectionType = getValidCollectionType(collectionType)

            val map: Map<String, String> = mapOf(
                STATE to state,
                START_DATE to startDate,
                END_DATE to endDate,
                GENRES to genres,
                SYNOPSIS to synopsis
            )

            configTabAdapter(map, collectionId, validCollectionType)
        }
    }

    private fun configTabAdapter(
        map: Map<String, String>,
        collectionId: String,
        validCollectionType: CollectionType
    ) {
        detailTabAdapter = DetailTabAdapter(
            this@DetailFragment,
            listOf<Fragment>(
                OverviewTabFragment.createFragment(map),
                ChaptersTabFragment.createFragment(collectionId, validCollectionType),
                CharactersTabFragment.createFragment(collectionId, validCollectionType)
            )
        )
        binding.vpInfo.adapter = detailTabAdapter
    }

    private fun getValidCollectionType(collectionType: String?): CollectionType {
        return when (collectionType) {
            CollectionType.MANGA.name -> CollectionType.MANGA
            else -> CollectionType.ANIME
        }
    }

    private fun configTabLayout() {
        TabLayoutMediator(binding.tlDetail, binding.vpInfo) { tab, position ->
            tab.text = when (position) {
                1 -> getString(resourcesR.string.chapters)
                2 -> getString(resourcesR.string.characters)
                else -> getString(resourcesR.string.overview)
            }
        }.attach()
    }

    override fun backNavigation(view: View) {
        findNavController().popBackStack()
    }
}
