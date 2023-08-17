package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.internal.datasource.room.dao.CollectionDao
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionEntity
import io.reactivex.rxjava3.core.Single

class CollectionRoomRepository(
    private val collectionDao: CollectionDao,
) : CollectionLocalRepository {
    override fun insertCollectionsEntities(collectionEntities: List<CollectionEntity>) {
        collectionDao.insertCollectionEntities(collectionEntities)
    }

    override fun collectPagedCollections(
        pageNumber: Int,
        requestType: RequestType,
    ): Single<List<CollectionEntity>> {
        return collectionDao.collectPagedCollectionsByTypeOfRequest(pageNumber, requestType.name)
    }

    override fun clearCollectionsByRequestType(requestType: RequestType) {
        collectionDao.clearCollectioncategoryjoinentity(requestType.name)
    }
}
