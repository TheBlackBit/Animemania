package com.theblackbit.animemania.android.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.feature.home.databinding.ItemDataBinding
import com.theblackbit.animemania.android.model.Collection

class DataViewHolder(
    private val binding: ItemDataBinding,

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(collection: Collection) {
        with(binding) {
            name = collection.name
            url = collection.miniPosterImageUrl
        }
    }
}
