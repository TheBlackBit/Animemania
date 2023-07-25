package com.theblackbit.animemania.android.home

import com.theblackbit.animemania.android.feature.home.R
import io.reactivex.rxjava3.disposables.Disposable

class MangaTabFragment : CollectionTabFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_tab_content

    override fun collectCategories(): Disposable {
        TODO("Not yet implemented")
    }
}
