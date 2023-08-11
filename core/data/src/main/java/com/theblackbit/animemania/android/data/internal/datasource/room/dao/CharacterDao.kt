package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CharacterEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterEntity>)

    // TODO: TEST LIMIT
    @Query("SELECT * from characterentity WHERE collectionID =:collectionId AND page =:page limit 20")
    fun getCharactersByCollectionId(collectionId: Int, page: Int): Single<List<CharacterEntity>>

    @Query("DELETE FROM characterentity WHERE collectionID =:collectionId")
    fun deleteCharactersByCollectionId(collectionId: Int)
}
