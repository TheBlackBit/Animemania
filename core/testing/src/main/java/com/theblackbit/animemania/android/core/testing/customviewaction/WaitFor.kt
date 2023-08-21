package com.theblackbit.animemania.android.core.testing.customviewaction

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

class WaitFor(private val delay: Long) : ViewAction {
    override fun getDescription(): String {
        return "wait for " + delay + "milliseconds"
    }

    override fun getConstraints(): Matcher<View> {
        return ViewMatchers.isRoot()
    }

    override fun perform(uiController: UiController?, view: View?) {
        uiController?.loopMainThreadForAtLeast(delay)
    }
}
