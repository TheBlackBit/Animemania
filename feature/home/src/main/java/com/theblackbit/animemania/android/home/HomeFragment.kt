package com.theblackbit.animemania.android.home

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.theblackbit.animemania.android.common.FragmentBindingCreator
import com.theblackbit.animemania.android.core.resources.R
import com.theblackbit.animemania.android.feature.home.databinding.FragmentHomeBinding
import com.theblackbit.animemania.android.home.adapter.HomeTabAdapter
import com.theblackbit.animemania.android.home.pagertabs.AnimeTabFragment
import com.theblackbit.animemania.android.home.pagertabs.MangaTabFragment
import com.theblackbit.animemania.android.feature.home.R as homeR

class HomeFragment : FragmentBindingCreator<FragmentHomeBinding>() {

    override val layoutId: Int
        get() = homeR.layout.fragment_home
    private lateinit var adapter: HomeTabAdapter

    private val fragments = listOf(
        AnimeTabFragment(),
        MangaTabFragment(),
    )

    companion object {
        const val CURRENT_TAB_POSITION = "CURRENT_TAB_POSITION"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configViewPagerAdapter()
        configTabLayout()
        savedInstanceState?.apply {
            binding.vpContent.currentItem = savedInstanceState.getInt(CURRENT_TAB_POSITION, 0)
        }
    }

    private fun configViewPagerAdapter() {
        adapter = HomeTabAdapter(this, fragments)
        binding.vpContent.adapter = adapter
        binding.vpContent.isSaveEnabled = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_TAB_POSITION, binding.vpContent.currentItem)
    }

    private fun configTabLayout() {
        TabLayoutMediator(binding.tlIndicator, binding.vpContent) { tab, position ->
            tab.text = if (position == 0) getString(R.string.anime) else getString(R.string.manga)
        }.attach()
    }
}
