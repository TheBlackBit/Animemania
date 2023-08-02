package com.theblackbit.animemania.android.detail.pagertabs.chapter

import androidx.recyclerview.widget.RecyclerView
import com.theblackbit.animemania.android.feature.detail.databinding.ItemChapterBinding
import com.theblackbit.animemania.android.model.Chapter

class ChapterViewHolder(
    private val binding: ItemChapterBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(chapter: Chapter) {
        with(binding) {
            name = "${chapter.number}. ${chapter.title}"
            imageUrl = chapter.imageUrl
        }
    }
}
