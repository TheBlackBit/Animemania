package com.theblackbit.animemania.android.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.feature.home.databinding.ItemDataContainerBinding

class DataContainerViewHolder(
    private val binding: ItemDataContainerBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        categoryName: String,
        dataAdapter: DataAdapter,
    ) {
        with(binding) {
            containerDataName = categoryName
            binding.rvDataContainer.adapter = dataAdapter
        }
    }
}
