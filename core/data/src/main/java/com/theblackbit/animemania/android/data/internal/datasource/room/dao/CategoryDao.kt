package com.theblackbit.animemania.android.data.internal.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CategoryEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategoryEntity(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM categoryentity where collectionType =:collectionType")
    fun getCategories(collectionType: String): Single<List<CategoryEntity>>

    @Query("DELETE from categoryentity where collectionType=:collectionType")
    fun clearCategoriesByCollectionType(
        collectionType: String,
    )
}
