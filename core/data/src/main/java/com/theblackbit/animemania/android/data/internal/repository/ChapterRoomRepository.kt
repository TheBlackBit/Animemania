package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.dao.ChapterDao
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.ChapterEntity
import io.reactivex.rxjava3.core.Single

class ChapterRoomRepository(
    private val chapterDao: ChapterDao,
) : ChapterLocalRepository {
    override fun insertChapters(chapters: List<ChapterEntity>) {
        chapterDao.insertChapters(chapters)
    }

    override fun getChaptersByCollection(collectionId: Int, pageNumber: Int): Single<List<ChapterEntity>> {
        return chapterDao.getChaptersByCollection(collectionId, pageNumber)
    }

    override fun deleteChaptersByCollection(collectionId: Int) {
        chapterDao.deleteChaptersByCollection(collectionId)
    }
}
