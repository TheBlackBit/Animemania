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
import java.util.concurrent.TimeUnit
import com.theblackbit.animemania.android.feature.home.R as homeR

class HomeFragment : FragmentBindingCreator<FragmentHomeBinding>() {

    override val layoutId: Int
        get() = homeR.layout.fragment_home

    private lateinit var adapter: HomeTabAdapter

    private val fragments = listOf(
        AnimeTabFragment(),
        MangaTabFragment(),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition(200, TimeUnit.MILLISECONDS)
        configViewPagerAdapter()
        configTabLayout()
    }

    private fun configViewPagerAdapter() {
        adapter = HomeTabAdapter(this, fragments)
        binding.vpContent.adapter = adapter
        binding.vpContent.isSaveEnabled = false
    }

    private fun configTabLayout() {
        TabLayoutMediator(binding.tlIndicator, binding.vpContent) { tab, position ->
            tab.text = if (position == 0) getString(R.string.anime) else getString(R.string.manga)
        }.attach()
    }
}
