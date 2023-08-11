package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CharacterEntity
import io.reactivex.rxjava3.core.Single

interface CharacterLocalRepository {
    fun insertCharacters(characters: List<CharacterEntity>)

    fun getCharactersByCollectionId(collectionId: Int, page: Int): Single<List<CharacterEntity>>

    fun deleteCharactersByCollectionId(collectionId: Int)
}
