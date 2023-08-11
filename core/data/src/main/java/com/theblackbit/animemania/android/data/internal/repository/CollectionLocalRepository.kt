package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionCategoryJoin
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionCategoryJoinEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionEntity
import io.reactivex.rxjava3.core.Single

interface CollectionLocalRepository {

    fun insertCollectionEntities(collectionEntities: List<CollectionEntity>)

    fun insertCollectionCategoryJoinEntities(collectionCategoryJoinEntities: List<CollectionCategoryJoinEntity>)

    fun collectPagedCollectionsByCategory(
        pageNumber: Int,
        categoryId: Int,
    ): Single<CollectionCategoryJoin>

    fun clearCollectioncategoryjoinentity(
        categoryId: Int,
    )

    fun clearCollectionEntitiesByCategory(
        categoryId: Int,
    )
}
