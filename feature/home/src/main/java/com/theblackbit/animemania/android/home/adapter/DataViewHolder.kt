package com.theblackbit.animemania.android.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.feature.home.databinding.ItemDataBinding
import com.theblackbit.animemania.android.model.Collection

class DataViewHolder(
    private val binding: ItemDataBinding,
    private val onClickCollection: DataAdapter.OnClickCollection,
    private val requestTypeName: String
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(collection: Collection) {
        with(binding) {
            name = collection.name
            url = collection.miniPosterImageUrl
            binding.ivPoster.transitionName = collection.collectionId.plus(requestTypeName)
            binding.flCollection.setOnClickListener {
                onClickCollection.onClick(collection, binding.ivPoster)
            }
            executePendingBindings()
        }
    }
}
