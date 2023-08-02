package com.theblackbit.animemania.android.detail.pagertabs.character

import androidx.recyclerview.widget.DiffUtil
import com.theblackbit.animemania.android.model.Character

class CharacterDiffCallBack : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}
