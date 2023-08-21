package com.theblackbit.animemania.android.core.testing.customviewmatcher

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.core.testing.exception.WrongViewTypeException
import com.theblackbit.animemania.android.core.testing.helpers.getChildRecyclerViewHolder
import org.hamcrest.BaseMatcher
import org.hamcrest.Description

class ImageIsCropAtPositionOnChildRecyclerView(
    private val positionOfChildRecyclerView: Int,
    private val childRecyclerViewId: Int,
    private val positionOfViewInChildRecyclerView: Int,
    private val imageViewId: Int
) : BaseMatcher<View>() {
    override fun describeTo(description: Description?) {
        description?.appendText("Assert ImageView has ScaleType CenterCrop at position $positionOfViewInChildRecyclerView in the position $positionOfChildRecyclerView of parent RecyclerView")
    }

    override fun matches(view: Any?): Boolean {
        if (view !is RecyclerView) return false

        val viewHolder = view.getChildRecyclerViewHolder(
            positionOfChildRecyclerView = positionOfChildRecyclerView,
            childRecyclerViewId = childRecyclerViewId,
            positionOfViewInChildRecyclerView = positionOfViewInChildRecyclerView
        )

        val imageView = viewHolder.itemView.findViewById<View>(imageViewId)
            ?: throw ClassNotFoundException("ImageView Not found check the id Of the ImageView")

        if (imageView !is ImageView) {
            throw WrongViewTypeException("The id of the ImageView does not correspond to a valid ImageView")
        }

        return imageView.scaleType == ImageView.ScaleType.CENTER_CROP
    }
}
