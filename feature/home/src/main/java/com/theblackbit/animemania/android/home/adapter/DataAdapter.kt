package com.theblackbit.animemania.android.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.theblackbit.animemania.android.common.extensions.viewForViewDataBinding
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.feature.home.databinding.ItemDataBinding
import com.theblackbit.animemania.android.model.Data

class DataAdapter(diffCallback: DataDiffCallback) :
    PagingDataAdapter<Data, DataViewHolder>(
        diffCallback,
    ) {

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let { data ->
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDataBinding.bind(parent.viewForViewDataBinding(R.layout.item_data))
        return DataViewHolder(
            binding,
        )
    }
}
