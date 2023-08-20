package com.theblackbit.animemania.android.core.testing.helpers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.core.testing.exception.ChildRecyclerViewNotFoundException

/**
 * Returns the ViewHolder of a child RecyclerView inside a parent RecyclerView at the specified positions.
 *
 * @param positionOfChildRecyclerView The position of the child RecyclerView in the parent RecyclerView.
 * @param childRecyclerViewId The ID of the child RecyclerView.
 * @param positionOfViewInChildRecyclerView The position of the view inside the child RecyclerView.
 * @return The ViewHolder of the view inside the child RecyclerView.
 * @throws ChildRecyclerViewNotFoundException If the child RecyclerView or its ViewHolder is not found at the specified positions.
 */
fun RecyclerView.getChildRecyclerViewHolder(
    positionOfChildRecyclerView: Int,
    childRecyclerViewId: Int,
    positionOfViewInChildRecyclerView: Int
): RecyclerView.ViewHolder {
    val viewHolder = findViewHolderForAdapterPosition(positionOfChildRecyclerView)
        ?: throw ChildRecyclerViewNotFoundException("No ViewHolder found at position $childRecyclerViewId")

    val innerRecyclerView =
        viewHolder.itemView.findViewById<View>(childRecyclerViewId)
            ?: throw ChildRecyclerViewNotFoundException("No RecyclerView found at position $childRecyclerViewId")

    if (innerRecyclerView !is RecyclerView) {
        throw ChildRecyclerViewNotFoundException(
            message = "The Id of childRecyclerView not correspond to a RecyclerView at position $childRecyclerViewId. Please verify the Id of your child RecyclerView"
        )
    }

    return innerRecyclerView.findViewHolderForAdapterPosition(positionOfViewInChildRecyclerView)
        ?: throw ChildRecyclerViewNotFoundException("No ViewHolder found for child RecyclerView at position $positionOfViewInChildRecyclerView")
}
