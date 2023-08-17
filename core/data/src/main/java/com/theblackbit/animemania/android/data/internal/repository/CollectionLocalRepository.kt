package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionEntity
import io.reactivex.rxjava3.core.Single

interface CollectionLocalRepository {

    fun insertCollectionsEntities(collectionEntities: List<CollectionEntity>)

    fun collectPagedCollections(
        pageNumber: Int,
        requestType: RequestType
    ): Single<List<CollectionEntity>>

    fun clearCollectionsByRequestType(
        requestType: RequestType
    )
}
