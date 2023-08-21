package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.view.View
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class WithImageScaleTypeAtPosition(
    private val position: Int,
    private val scaleType: ScaleType,
    private val imageViewId: Int
) : BaseMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("ScaleType of ImageView with id $imageViewId at position $position is ${scaleType.name}.")
    }

    override fun matches(view: Any?): Boolean {
        if (view !is RecyclerView) return false

        val viewHolder = view.findViewHolderForAdapterPosition(position)

        val imageView = viewHolder?.itemView?.findViewById<View>(imageViewId)
        if (imageView !is ImageView) return false

        return imageView.scaleType == scaleType
    }
}
