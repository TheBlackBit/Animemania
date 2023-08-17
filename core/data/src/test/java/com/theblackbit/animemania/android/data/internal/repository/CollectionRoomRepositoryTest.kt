package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.internal.datasource.room.dao.CollectionDao
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CollectionRoomRepositoryTest {

    @Mock
    private lateinit var collectionDao: CollectionDao

    private lateinit var sut: CollectionRoomRepository

    @Before
    fun setUp() {
        sut = CollectionRoomRepository(collectionDao)
    }

    @Test
    fun testInsertCollectionEntitiesByDao() {
        val collectionEntities = emptyList<CollectionEntity>()

        sut.insertCollectionsEntities(collectionEntities)

        Mockito.verify(collectionDao).insertCollectionEntities(collectionEntities)
    }

    @Test
    fun testClearCollectionEntitiesByCategoryByDao() {
        sut.clearCollectionsByRequestType(RequestType.TRENDING_MANGA)

        Mockito.verify(collectionDao)
            .clearCollectioncategoryjoinentity(RequestType.TRENDING_MANGA.name)
    }
}
