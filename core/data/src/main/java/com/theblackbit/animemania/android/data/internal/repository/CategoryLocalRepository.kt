package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CategoryEntity
import io.reactivex.rxjava3.core.Single

interface CategoryLocalRepository {
    fun insertCategoryEntity(categoryEntity: CategoryEntity)

    fun getCategories(collectionType: String): Single<List<CategoryEntity>>

    fun clearCategoriesByCollectionType(
        collectionType: String,
    )
}
