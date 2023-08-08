package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.ChapterEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface ChapterDao {
    @Insert
    fun insertChapters(chapters: List<ChapterEntity>)

    @Query("SELECT * from chapterentity WHERE collectionID =:collectionId")
    fun getChaptersByCollection(collectionId: Int): Single<List<ChapterEntity>>

    @Query("DELETE FROM chapterentity WHERE collectionID =:collectionId")
    fun deleteChaptersByCollection(collectionId: Int)
}
