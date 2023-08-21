package com.theblackbit.animemania.android.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.theblackbit.animemania.android.model.Collection

class DataDiffCallback : DiffUtil.ItemCallback<Collection>() {

    override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
        return oldItem.collectionId == newItem.collectionId
    }

    override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
        return oldItem == newItem
    }
}
