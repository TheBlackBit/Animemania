package com.theblackbit.animemania.android.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.feature.home.databinding.ItemDataBinding
import com.theblackbit.animemania.android.model.Data

class DataViewHolder(
    private val binding: ItemDataBinding,

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: com.theblackbit.animemania.android.model.Data) {
        with(binding) {
            name = data.attributes.name
            url = data.attributes.miniPosterImageUrl
        }
    }
}
