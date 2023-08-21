package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.view.View
import android.widget.ImageView
import com.theblackbit.animemania.android.core.testing.exception.WrongViewTypeException
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class ImageIsLoadedMatcher : BaseMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("ImageView resource is loaded")
    }

    override fun matches(view: Any): Boolean {
        if (view !is ImageView) throw WrongViewTypeException("${view.javaClass.name} is not a valid ImageView}")
        return view.drawable != null
    }
}
