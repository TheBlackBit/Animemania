package com.theblackbit.animemania.android.home.adapter

import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import com.theblackbit.animemania.android.common.extensions.viewForViewDataBinding
import com.theblackbit.animemania.android.feature.home.R
import com.theblackbit.animemania.android.feature.home.databinding.ItemDataBinding
import com.theblackbit.animemania.android.model.Collection

class DataAdapter(
    diffCallback: DataDiffCallback,
    private val onClickCollection: OnClickCollection,
) :
    PagingDataAdapter<Collection, DataViewHolder>(
        diffCallback,
    ) {

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let { collection ->
            holder.bind(collection)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDataBinding.bind(parent.viewForViewDataBinding(R.layout.item_data))
        return DataViewHolder(
            binding = binding,
            onClickCollection = onClickCollection,
        )
    }

    interface OnClickCollection {
        fun onClick(collection: Collection, cardView: CardView)
    }
}
