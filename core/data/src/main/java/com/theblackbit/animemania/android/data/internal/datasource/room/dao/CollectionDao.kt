package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionCategoryJoin
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionCategoryJoinEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollectionEntities(collectionEntities: List<CollectionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollectionCategoryJoinEntities(collectionCategoryJoinEntities: List<CollectionCategoryJoinEntity>)

    // TODO: TEST LIMIT
    @Transaction
    @Query("SELECT * from collectioncategoryjoinentity where pageNumber =:pageNumber AND categoryId =:categoryId limit 20")
    fun collectPagedCollectionsByCategory(
        pageNumber: Int,
        categoryId: Int,
    ): Single<CollectionCategoryJoin>

    @Query("DELETE from collectioncategoryjoinentity where categoryId=:categoryId")
    fun clearCollectioncategoryjoinentity(
        categoryId: Int,
    )

    @Query("DELETE from collectionentity where categoryId=:categoryId")
    fun clearCollectionEntitiesByCategory(
        categoryId: Int,
    )
}
