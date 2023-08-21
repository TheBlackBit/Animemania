package com.theblackbit.animemania.android.detail.pagertabs.character

import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.feature.detail.databinding.ItemCharacterBinding
import com.theblackbit.animemania.android.model.Character

class CharactersViewHolder(
    private val binding: ItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        with(binding) {
            name = character.name
            imageUrl = character.imageUrl
        }
    }
}
