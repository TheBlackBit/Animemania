package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class WithTextAtPosition(
    private val text: String,
    private val position: Int,
    private val textViewId: Int
) : BaseMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("Check $text matches with textView with id $textViewId at position $position")
    }

    override fun matches(view: Any): Boolean {
        if (view !is RecyclerView) return false

        val viewHolder = view.findViewHolderForAdapterPosition(position)

        val textView = viewHolder?.itemView?.findViewById<View>(textViewId)
        if (textView !is TextView) return false

        return textView.text.toString() == text
    }
}
