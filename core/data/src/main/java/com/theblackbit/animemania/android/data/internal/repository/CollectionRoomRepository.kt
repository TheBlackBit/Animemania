package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.dao.CollectionDao
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionCategoryJoin
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionCategoryJoinEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionEntity
import io.reactivex.rxjava3.core.Single

class CollectionRoomRepository(
    private val collectionDao: CollectionDao,
) : CollectionLocalRepository {
    override fun insertCollectionEntities(collectionEntities: List<CollectionEntity>) {
        collectionDao.insertCollectionEntities(collectionEntities)
    }

    override fun insertCollectionCategoryJoinEntities(collectionCategoryJoinEntities: List<CollectionCategoryJoinEntity>) {
        collectionDao.insertCollectionCategoryJoinEntities(collectionCategoryJoinEntities)
    }

    override fun collectPagedCollectionsByCategory(
        pageNumber: Int,
        categoryId: Int,
    ): Single<CollectionCategoryJoin> {
        return collectionDao.collectPagedCollectionsByCategory(
            pageNumber = pageNumber,
            categoryId = categoryId,
        )
    }

    override fun clearCollectioncategoryjoinentity(categoryId: Int) {
        collectionDao.clearCollectioncategoryjoinentity(categoryId)
    }

    override fun clearCollectionEntitiesByCategory(categoryId: Int) {
        collectionDao.clearCollectionEntitiesByCategory(categoryId)
    }
}
