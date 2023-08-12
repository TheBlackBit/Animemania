package com.theblackbit.animemania.android.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
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
import com.theblackbit.animemania.android.common.FragmentBindingCreator
import com.theblackbit.animemania.android.common.OnBackNavigation
import com.theblackbit.animemania.android.detail.adapter.DetailTabAdapter
import com.theblackbit.animemania.android.detail.pagertabs.OverviewTabFragment
import com.theblackbit.animemania.android.detail.pagertabs.chapter.ChaptersTabFragment
import com.theblackbit.animemania.android.detail.pagertabs.character.CharactersTabFragment
import com.theblackbit.animemania.android.feature.detail.R
import com.theblackbit.animemania.android.feature.detail.databinding.FragmentDetailBinding
import com.theblackbit.animemania.android.model.CollectionType
import com.theblackbit.animemania.android.core.resources.R as resourcesR

class DetailFragment : FragmentBindingCreator<FragmentDetailBinding>(), OnBackNavigation {
    override val layoutId: Int
        get() = R.layout.fragment_detail

    private lateinit var detailTabAdapter: DetailTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            val collectionId = getString(COLLECTION_ID, "")
            val coverImageVal = getString(COVER_IMAGE, "")
            val posterImageVal = getString(POSTER_IMAGE, "")
            val titleVal = getString(TITLE, "")
            val ratingVal = getString(RATING, "")
            val state = getString(STATE, "")
            val startDate = getString(START_DATE, "")
            val endDate = getString(END_DATE, "")
            val genres = getString(GENRES, "")
            val overview = getString(SYNOPSIS, "")
            val collectionType = getString(COLLECTION_TYPE, "")

            binding.apply {
                cover.transitionName = collectionId
                coverImage = coverImageVal
                posterImage = posterImageVal
                title = titleVal
                rating = ratingVal
                bacKNavigation = this@DetailFragment
            }
            configViewPagerWithTabs(
                collectionId = collectionId,
                state = state,
                startDate = startDate,
                endDate = endDate,
                genres = genres,
                overview = overview,
                collectionType = getValidCollectionType(collectionType),
            )
            configTabLayout()
        }
    }

    private fun getValidCollectionType(collectionType: String?): CollectionType {
        return when (collectionType) {
            CollectionType.MANGA.name -> CollectionType.MANGA
            else -> CollectionType.ANIME
        }
    }

    private fun configViewPagerWithTabs(
        collectionId: String,
        collectionType: CollectionType,
        state: String,
        startDate: String,
        endDate: String,
        genres: String,
        overview: String,

    ) {
        detailTabAdapter = DetailTabAdapter(
            this,
            listOf<Fragment>(
                OverviewTabFragment.createFragment(
                    state = state,
                    startDate = startDate,
                    endDate = endDate,
                    genres = genres,
                    synopsis = overview,
                ),
                ChaptersTabFragment.createFragment(collectionId, collectionType),
                CharactersTabFragment.createFragment(collectionId, collectionType),
            ),
        )
        binding.vpInfo.adapter = detailTabAdapter
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
