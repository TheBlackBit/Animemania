package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollectionEntities(collectionEntities: List<CollectionEntity>)

    @Transaction
    @Query("SELECT * from collectionentity where page =:pageNumber AND typeOfRequest =:typeOfRequest")
    fun collectPagedCollectionsByTypeOfRequest(
        pageNumber: Int,
        typeOfRequest: String,
    ): Single<List<CollectionEntity>>

    @Query("DELETE from collectionentity  where typeOfRequest=:typeOfRequest")
    fun clearCollectioncategoryjoinentity(
        typeOfRequest: String,
    )
}
