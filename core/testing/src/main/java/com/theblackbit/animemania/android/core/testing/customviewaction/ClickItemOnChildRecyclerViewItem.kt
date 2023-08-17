package com.theblackbit.animemania.android.core.testing.customviewaction

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

class ClickItemOnChildRecyclerViewItem<T : ViewHolder>(
    private val parentRecyclerViewId: Int,
    private val positionOfChildRecyclerView: Int,
    private val positionOfViewInChildRecyclerView: Int,
    private val childRecyclerViewId: Int
) : ViewAction {
    override fun getDescription(): String {
        return "Click to $positionOfViewInChildRecyclerView child position in the $positionOfChildRecyclerView parent position"
    }

    override fun getConstraints(): Matcher<View> {
        return ViewMatchers.withId(parentRecyclerViewId)
    }

    override fun perform(uiController: UiController?, view: View?) {
        if (view !is RecyclerView) return
        val viewHolder = view.findViewHolderForAdapterPosition(positionOfChildRecyclerView)

        val innerRecyclerView =
            viewHolder?.itemView?.findViewById<RecyclerView>(childRecyclerViewId)

        val action = RecyclerViewActions.actionOnItemAtPosition<T>(
            positionOfViewInChildRecyclerView,
            click()
        )
        action.perform(uiController, innerRecyclerView)
    }
}
