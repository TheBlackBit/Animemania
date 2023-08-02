package com.theblackbit.animemania.android.detail.pagertabs

import android.os.Bundle
import android.view.View
import com.theblackbit.animemania.android.common.FragmentBindingCreator
import com.theblackbit.animemania.android.feature.detail.R
import com.theblackbit.animemania.android.feature.detail.databinding.FragmentTabOverviewBinding

class OverviewTabFragment : FragmentBindingCreator<FragmentTabOverviewBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_tab_overview

    companion object {

        const val STATE: String = "state"
        const val START_DATE: String = "startDate"
        const val END_DATE: String = "endDate"
        const val GENRES: String = "genres"
        const val SYNOPSIS: String = "overview"

        fun createFragment(
            state: String,
            startDate: String,
            endDate: String,
            genres: String,
            synopsis: String,
        ): OverviewTabFragment {
            val bundle = Bundle()

            bundle.apply {
                putString(STATE, state)
                putString(START_DATE, startDate)
                putString(END_DATE, endDate)
                putString(GENRES, genres)
                putString(SYNOPSIS, synopsis)
            }

            val fragment = OverviewTabFragment()

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            val stateVal = getString(STATE, "")
            val startDateVal = getString(START_DATE, "")
            val endDateVal = getString(END_DATE, "")
            val genresVal = getString(GENRES, "")
            val synopsisVal = getString(SYNOPSIS, "")

            binding.apply {
                state = stateVal
                showState = stateVal.isNotEmpty()
                startDate = startDateVal
                showStartDate = startDateVal.isNotEmpty()
                endDate = endDateVal
                showEndDate = endDateVal.isNotEmpty()
                genres = genresVal
                showGenres = genresVal.isNotEmpty()
                synopsis = synopsisVal
                showEmptySynopsis = synopsisVal.isEmpty()
            }
        }
    }
}
