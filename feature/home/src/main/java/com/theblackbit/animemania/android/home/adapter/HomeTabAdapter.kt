package com.theblackbit.animemania.android.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.theblackbit.animemania.android.home.AnimeTabFragment
import com.theblackbit.animemania.android.home.MangaTabFragment

class HomeTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentsList: List<Fragment> = listOf(
        AnimeTabFragment(),
        MangaTabFragment(),
    )

    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position]
    }
}
