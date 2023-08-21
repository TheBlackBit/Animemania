package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.core.testing.exception.WrongViewTypeException
import com.theblackbit.animemania.android.core.testing.helpers.getChildRecyclerViewHolder
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class MaxLinesTextViewAtPositionOnChildRecyclerView(
    private val positionOfChildRecyclerView: Int,
    private val childRecyclerViewId: Int,
    private val positionOfViewInChildRecyclerView: Int,
    private val textViewId: Int,
    private val maxLines: Int
) : BaseMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("")
    }

    override fun matches(view: Any?): Boolean {
        if (view !is RecyclerView) return false
        val viewHolder = view.getChildRecyclerViewHolder(
            positionOfChildRecyclerView = positionOfChildRecyclerView,
            childRecyclerViewId = childRecyclerViewId,
            positionOfViewInChildRecyclerView = positionOfViewInChildRecyclerView
        )

        val textView = viewHolder.itemView.findViewById<View>(textViewId)
            ?: throw ClassNotFoundException("TextView Not found check the id Of the TextView")

        if (textView !is TextView) {
            throw WrongViewTypeException("The id of the textView does not correspond to a valid TextView")
        }

        return textView.maxLines == maxLines
    }
}
