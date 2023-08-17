package com.theblackbit.animemania.android.detail.pagertabs.character

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.theblackbit.animemania.android.common.extensions.viewForViewDataBinding
import com.theblackbit.animemania.android.feature.detail.R
import com.theblackbit.animemania.android.feature.detail.databinding.ItemCharacterBinding
import com.theblackbit.animemania.android.model.Character

class CharactersAdapter(diffCallBack: CharacterDiffCallBack) :
    PagingDataAdapter<Character, CharactersViewHolder>(diffCallBack) {
    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let { character ->
            holder.bind(character)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding =
            ItemCharacterBinding.bind(parent.viewForViewDataBinding(R.layout.item_character))
        return CharactersViewHolder(
            binding
        )
    }
}
