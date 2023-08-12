package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.dao.CharacterDao
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CharacterEntity
import io.reactivex.rxjava3.core.Single

class CharacterRoomRepository(
    private val characterDao: CharacterDao,
) : CharacterLocalRepository {
    override fun insertCharacters(characters: List<CharacterEntity>) {
        characterDao.insertCharacters(characters)
    }

    override fun getCharactersByCollectionId(collectionId: String, page: Int): Single<List<CharacterEntity>> {
        return characterDao.getCharactersByCollectionId(collectionId, page)
    }

    override fun deleteCharactersByCollectionId(collectionId: String) {
        characterDao.deleteCharactersByCollectionId(collectionId)
    }
}
