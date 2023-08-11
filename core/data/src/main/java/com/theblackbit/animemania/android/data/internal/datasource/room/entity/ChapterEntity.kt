package com.theblackbit.animemania.android.data.internal.datasource.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.theblackbit.animemania.android.model.Chapter

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
    val pageNumber: Int,
)

fun ChapterEntity.toChapterModel(): Chapter {
    return Chapter(
        id = chapterId.toString(),
        title = title,
        synopsis = synopsis,
        number = number.toString(),
        seasonNumber = seasonNumber.toString(),
        imageUrl = imageUrl,
    )
}
