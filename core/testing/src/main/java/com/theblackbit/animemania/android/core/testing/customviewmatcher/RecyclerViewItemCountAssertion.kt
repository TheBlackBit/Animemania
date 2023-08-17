package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class RecyclerViewItemCountAssertion(
    private val expectedCount: Int,
) : BaseMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("")
    }

    override fun matches(view: Any?): Boolean {
        if (view !is RecyclerView) return false
        return view.adapter?.itemCount == expectedCount
    }
}
