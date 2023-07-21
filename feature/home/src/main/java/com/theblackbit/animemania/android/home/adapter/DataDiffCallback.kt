package com.theblackbit.animemania.android.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.theblackbit.animemania.android.model.Data

class DataDiffCallback : DiffUtil.ItemCallback<com.theblackbit.animemania.android.model.Data>() {

    override fun areItemsTheSame(oldItem: com.theblackbit.animemania.android.model.Data, newItem: com.theblackbit.animemania.android.model.Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: com.theblackbit.animemania.android.model.Data, newItem: com.theblackbit.animemania.android.model.Data): Boolean {
        return oldItem == newItem
    }
}
