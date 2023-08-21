package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.core.testing.exception.WrongViewTypeException
import com.theblackbit.animemania.android.core.testing.helpers.getChildRecyclerViewHolder
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

/**
 * Custom matcher to check if a TextView at a given position inside a child RecyclerView within a parent RecyclerView
 * has the ellipsize mode set to "END".
 *
 * @param positionOfChildRecyclerView The position of the child RecyclerView in the parent RecyclerView.
 * @param childRecyclerViewId The ID of the child RecyclerView.
 * @param positionOfViewInChildRecyclerView The position of the view inside the child RecyclerView.
 * @param textViewId The ID of the TextView inside the child RecyclerView.
 */
class HasEllipsizeEndAtPositionOnChildRecyclerView(
    private val positionOfChildRecyclerView: Int,
    private val childRecyclerViewId: Int,
    private val positionOfViewInChildRecyclerView: Int,
    private val textViewId: Int
) : BaseMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("Assert TextView if has Ellipsize End at position $positionOfViewInChildRecyclerView in the position $positionOfChildRecyclerView of parent RecyclerView")
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

        return textView.ellipsize == TextUtils.TruncateAt.END
    }
}
