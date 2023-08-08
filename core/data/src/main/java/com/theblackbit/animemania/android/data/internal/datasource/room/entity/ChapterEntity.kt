package com.theblackbit.animemania.android.data.internal.datasource.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChapterEntity(
    @PrimaryKey
    val chapterId: Int,
    val title: String,
    val synopsis: String,
    val collectionId: Int,
    val number: Int,
    val seasonNumber: Int,
    val imageUrl: String,
)
