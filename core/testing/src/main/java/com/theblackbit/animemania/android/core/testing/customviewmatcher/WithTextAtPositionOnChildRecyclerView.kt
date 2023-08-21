package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.core.testing.exception.WrongViewTypeException
import com.theblackbit.animemania.android.core.testing.helpers.getChildRecyclerViewHolder
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

/**
 * Custom matcher to check if a TextView with the specified text exists at a given position
 * inside a child RecyclerView within a parent RecyclerView.
 *
 * @param positionOfChildRecyclerView The position of the child RecyclerView in the parent RecyclerView.
 * @param childRecyclerViewId The ID of the child RecyclerView.
 * @param positionOfViewInChildRecyclerView The position of the view inside the child RecyclerView.
 * @param textViewId The ID of the TextView inside the child RecyclerView.
 * @param text The text to match with the TextView's text.
 */
class WithTextAtPositionOnChildRecyclerView(
    private val positionOfChildRecyclerView: Int,
    private val childRecyclerViewId: Int,
    private val positionOfViewInChildRecyclerView: Int,
    private val textViewId: Int,
    private val text: String
) : BaseMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("Assert of recycler view at position $positionOfViewInChildRecyclerView in the $positionOfChildRecyclerView position of the parent")
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
        return textView.text.toString() == text
    }
}
