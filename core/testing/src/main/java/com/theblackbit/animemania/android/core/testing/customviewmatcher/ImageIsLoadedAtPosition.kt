package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class ImageIsLoadedAtPosition(
    private val position: Int,
    private val imageViewId: Int
) : BaseMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("Image is loaded with id $imageViewId at position $position")
    }

    override fun matches(view: Any?): Boolean {
        if (view !is RecyclerView) return false

        val viewHolder = view.findViewHolderForAdapterPosition(position)

        val imageView = viewHolder?.itemView?.findViewById<View>(imageViewId)
        if (imageView !is ImageView) return false

        return imageView.drawable != null
    }
}
