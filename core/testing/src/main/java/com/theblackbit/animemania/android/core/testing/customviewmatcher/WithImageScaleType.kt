package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.view.View
import android.widget.ImageView
import com.theblackbit.animemania.android.core.testing.exception.WrongViewTypeException
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class WithImageScaleType(
    private val scaleType: ImageView.ScaleType,
) : BaseMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("ScaleType for image view is ${scaleType.name}")
    }

    override fun matches(view: Any): Boolean {
        if (view !is ImageView) throw WrongViewTypeException("${view.javaClass.name} is not a valid ImageView}")
        return view.scaleType == scaleType
    }
}
