package com.theblackbit.animemania.android.detail.pagertabs.chapter

import androidx.recyclerview.widget.DiffUtil
import com.theblackbit.animemania.android.model.Chapter

class ChapterDiffCallback : DiffUtil.ItemCallback<Chapter>() {
    override fun areItemsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Chapter, newItem: Chapter): Boolean {
        return oldItem == newItem
    }
}
