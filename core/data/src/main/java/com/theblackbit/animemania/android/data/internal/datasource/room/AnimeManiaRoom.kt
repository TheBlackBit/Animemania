package com.theblackbit.animemania.android.data.internal.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.theblackbit.animemania.android.data.internal.datasource.room.dao.ChapterDao
import com.theblackbit.animemania.android.data.internal.datasource.room.dao.CharacterDao
import com.theblackbit.animemania.android.data.internal.datasource.room.dao.CollectionDao
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.ChapterEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CharacterEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionEntity

const val LATEST_VERSION = 1

@Database(
    version = LATEST_VERSION,
    entities = [
        CollectionEntity::class,
        CharacterEntity::class,
        ChapterEntity::class,
    ],
    exportSchema = true,
)
abstract class AnimeManiaRoom : RoomDatabase() {
    abstract fun collectionDao(): CollectionDao
    abstract fun chapterDao(): ChapterDao
    abstract fun characterDao(): CharacterDao
}
