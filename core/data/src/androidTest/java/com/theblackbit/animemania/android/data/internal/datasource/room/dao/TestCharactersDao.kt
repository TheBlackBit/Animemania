package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.theblackbit.animemania.android.data.internal.datasource.room.AnimeManiaRoom
import com.theblackbit.animemania.android.data.internal.datasource.room.dao.data.characterList
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestCharactersDao {

    private lateinit var db: AnimeManiaRoom

    private lateinit var characterDao: CharacterDao

    private val collectionId = 1
    private val page = 1

    // TODO: RUN TEST
    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AnimeManiaRoom::class.java)
            .build()
        characterDao = db.characterDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertAndCollectCharacters() {
        characterDao.insertCharacters(characterList)
        characterDao.getCharactersByCollectionId(collectionId, page = page)
            .test()
            .assertValue { characterEntity ->
                characterEntity.isNotEmpty() &&
                    characterEntity.all {
                        it.collectionId == collectionId
                    }
            }
    }

    @Test
    fun testDeleteCharacters() {
        characterDao.insertCharacters(characterList)
        characterDao.deleteCharactersByCollectionId(collectionId)
        characterDao.getCharactersByCollectionId(collectionId, page = page)
            .test()
            .assertValue { characterEntities ->
                characterEntities.isEmpty()
            }
    }
}
