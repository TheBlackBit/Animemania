package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.ChapterEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface ChapterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChapters(chapters: List<ChapterEntity>)

    // TODO: TEST LIMIT
    @Query("SELECT * from chapterentity WHERE collectionID =:collectionId AND pageNumber =:pageNumber LIMIT 20")
    fun getChaptersByCollection(collectionId: String, pageNumber: Int): Single<List<ChapterEntity>>

    @Query("DELETE FROM chapterentity WHERE collectionID =:collectionId")
    fun deleteChaptersByCollection(collectionId: String)
}
