package com.theblackbit.animemania.android.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.common.extensions.viewForViewDataBinding
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.feature.home.databinding.ItemDataContainerBinding

class DataContainerAdapter(
    private val data: List<Pair<DataAdapter, String>>
) : RecyclerView.Adapter<DataContainerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataContainerViewHolder {
        val binding =
            ItemDataContainerBinding.bind(parent.viewForViewDataBinding(R.layout.item_data_container))
        return DataContainerViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DataContainerViewHolder, position: Int) {
        data[position].let { data ->
            holder.bind(data.second, data.first)
        }
    }
}
