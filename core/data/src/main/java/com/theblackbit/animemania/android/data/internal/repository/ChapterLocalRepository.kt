package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.entity.ChapterEntity
import io.reactivex.rxjava3.core.Single

interface ChapterLocalRepository {
    fun insertChapters(chapters: List<ChapterEntity>)

    fun getChaptersByCollection(collectionId: Int, pageNumber: Int): Single<List<ChapterEntity>>

    fun deleteChaptersByCollection(collectionId: Int)
}
