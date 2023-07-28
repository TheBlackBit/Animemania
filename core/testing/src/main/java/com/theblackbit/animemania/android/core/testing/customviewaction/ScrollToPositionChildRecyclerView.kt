package com.theblackbit.animemania.android.core.testing.customviewaction

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher

/**
 * ViewAction to scroll to a specific position in a child RecyclerView inside a parent RecyclerView.
 *
 * @param parentRecyclerViewId The ID of the parent RecyclerView.
 * @param positionOfChildRecyclerView The position of the child RecyclerView in the parent RecyclerView.
 * @param positionOfViewInChildRecyclerView The position to scroll to inside the child RecyclerView.
 * @param childRecyclerViewId The ID of the child RecyclerView.
 */
class ScrollToPositionChildRecyclerView(
    private val parentRecyclerViewId: Int,
    private val positionOfChildRecyclerView: Int,
    private val positionOfViewInChildRecyclerView: Int,
    private val childRecyclerViewId: Int,

) : ViewAction {
    override fun getDescription(): String {
        return "Scroll to $positionOfViewInChildRecyclerView child position in the $positionOfChildRecyclerView parent position"
    }

    override fun getConstraints(): Matcher<View> {
        return withId(parentRecyclerViewId)
    }

    override fun perform(uiController: UiController?, view: View?) {
        if (view !is RecyclerView) return
        val viewHolder = view.findViewHolderForAdapterPosition(positionOfChildRecyclerView)

        val innerRecyclerView =
            viewHolder?.itemView?.findViewById<RecyclerView>(childRecyclerViewId)

        val action = RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
            positionOfViewInChildRecyclerView,
        )
        action.perform(uiController, innerRecyclerView)
        uiController?.loopMainThreadForAtLeast(1000) // Optional delay after scrolling in order to wait the movement of the item
    }
}
