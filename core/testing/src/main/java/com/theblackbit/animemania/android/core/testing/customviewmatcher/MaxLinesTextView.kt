package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class MaxLinesTextView(
    private val position: Int,
    private val maxLines: Int,
    private val textViewId: Int,
) : BaseMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("Max Lines of TextView with id $textViewId at position $position is $maxLines")
    }

    override fun matches(view: Any?): Boolean {
        if (view !is RecyclerView) return false

        val viewHolder = view.findViewHolderForAdapterPosition(position)

        val textView = viewHolder?.itemView?.findViewById<View>(textViewId)
        if (textView !is TextView) return false

        return textView.maxLines == maxLines
    }
}
