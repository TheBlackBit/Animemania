package com.theblackbit.animemania.android.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.theblackbit.animemania.android.home.HomeTabContentFragment
import com.theblackbit.animemania.android.util.TypeOfData

class HomeTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentsList: List<Fragment> = listOf(
        HomeTabContentFragment.newInstance(TypeOfData.ANIME),
        HomeTabContentFragment.newInstance(TypeOfData.MANGA),
    )

    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position]
    }
}
