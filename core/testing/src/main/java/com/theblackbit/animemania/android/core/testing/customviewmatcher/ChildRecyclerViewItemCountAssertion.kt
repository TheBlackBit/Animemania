package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.core.testing.exception.ChildRecyclerViewNotFoundException
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class ChildRecyclerViewItemCountAssertion(
    private val positionOfChildRecyclerView: Int,
    private val childRecyclerViewId: Int,
    private val expectedCount: Int
) : BaseMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("")
    }

    override fun matches(view: Any?): Boolean {
        if (view !is RecyclerView) return false

        val viewHolder = view.findViewHolderForAdapterPosition(positionOfChildRecyclerView)
            ?: throw ChildRecyclerViewNotFoundException("No ViewHolder found at position $childRecyclerViewId")

        val innerRecyclerView =
            viewHolder.itemView.findViewById<View>(childRecyclerViewId)
                ?: throw ChildRecyclerViewNotFoundException("No RecyclerView found at position $childRecyclerViewId")

        if (innerRecyclerView !is RecyclerView) {
            throw ChildRecyclerViewNotFoundException(
                message = "The Id of childRecyclerView not correspond to a RecyclerView at position $childRecyclerViewId. Please verify the Id of your child RecyclerView"
            )
        }

        return innerRecyclerView.adapter?.itemCount == expectedCount
    }
}
