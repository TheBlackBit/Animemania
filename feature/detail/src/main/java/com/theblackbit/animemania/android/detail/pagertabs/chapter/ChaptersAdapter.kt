package com.theblackbit.animemania.android.detail.pagertabs.chapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.theblackbit.animemania.android.common.extensions.viewForViewDataBinding
import com.theblackbit.animemania.android.feature.detail.R
import com.theblackbit.animemania.android.feature.detail.databinding.ItemChapterBinding
import com.theblackbit.animemania.android.model.Chapter

class ChaptersAdapter(diffCallback: ChapterDiffCallback) :
    PagingDataAdapter<Chapter, ChapterViewHolder>(
        diffCallback,
    ) {

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        getItem(position)?.let { chapter ->
            holder.bind(chapter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val binding = ItemChapterBinding.bind(parent.viewForViewDataBinding(R.layout.item_chapter))
        return ChapterViewHolder(
            binding,
        )
    }
}
