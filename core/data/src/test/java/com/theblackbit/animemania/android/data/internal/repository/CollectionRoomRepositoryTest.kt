package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.dao.CollectionDao
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionCategoryJoinEntity
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

        sut.insertCollectionEntities(collectionEntities)

        Mockito.verify(collectionDao).insertCollectionEntities(collectionEntities)
    }

    @Test
    fun testInsertCollectionCategoryJoinEntitiesByDao() {
        val collectionCategoryJoinEntities = emptyList<CollectionCategoryJoinEntity>()

        sut.insertCollectionCategoryJoinEntities(collectionCategoryJoinEntities)

        Mockito.verify(collectionDao)
            .insertCollectionCategoryJoinEntities(collectionCategoryJoinEntities)
    }

    @Test
    fun testCollectPagedCollectionsByCategoryByDao() {
        val pageNumber = 1
        val categoryId = 1

        sut.collectPagedCollectionsByCategory(pageNumber = pageNumber, categoryId = categoryId)

        Mockito.verify(collectionDao)
            .collectPagedCollectionsByCategory(pageNumber, categoryId)
    }

    @Test
    fun testClearCollectioncategoryjoinentityByDao() {
        val categoryId = 1

        sut.clearCollectioncategoryjoinentity(categoryId)

        Mockito.verify(collectionDao)
            .clearCollectioncategoryjoinentity(categoryId)
    }

    @Test
    fun testClearCollectionEntitiesByCategoryByDao() {
        val categoryId = 1

        sut.clearCollectionEntitiesByCategory(categoryId)

        Mockito.verify(collectionDao)
            .clearCollectionEntitiesByCategory(categoryId)
    }
}
