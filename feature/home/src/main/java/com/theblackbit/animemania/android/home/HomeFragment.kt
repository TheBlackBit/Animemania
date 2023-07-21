package com.theblackbit.animemania.android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.theblackbit.animemania.android.feature.home.databinding.FragmentHomeBinding
import com.theblackbit.animemania.android.home.adapter.HomeTabAdapter
import com.theblackbit.animemania.android.resources.R

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeTabAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configViewPagerAdapter()
        configTabLayout()
    }

    private fun configViewPagerAdapter() {
        adapter = HomeTabAdapter(this)
        binding.vpContent.adapter = adapter
    }

    private fun configTabLayout() {
        TabLayoutMediator(binding.tlIndicator, binding.vpContent) { tab, position ->
            tab.text = if (position == 0) getString(R.string.anime) else getString(R.string.manga)
        }.attach()
    }
}
