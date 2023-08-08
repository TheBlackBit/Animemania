package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CharacterEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface CharacterDao {
    @Insert
    fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * from characterentity WHERE collectionID =:collectionId")
    fun getCharactersByCollectionId(collectionId: Int): Single<List<CharacterEntity>>

    @Query("DELETE FROM characterentity WHERE collectionID =:collectionId")
    fun deleteCharactersByCollectionId(collectionId: Int)
}
