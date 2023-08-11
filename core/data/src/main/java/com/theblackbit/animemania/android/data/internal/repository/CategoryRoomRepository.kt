package com.theblackbit.animemania.android.data.internal.repository

import com.theblackbit.animemania.android.data.internal.datasource.room.dao.CategoryDao
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CategoryEntity
import io.reactivex.rxjava3.core.Single

class CategoryRoomRepository(
    private val categoryDao: CategoryDao,
) : CategoryLocalRepository {
    override fun insertCategoryEntity(categoryEntity: CategoryEntity) {
        categoryDao.insertCategoryEntity(categoryEntity)
    }

    override fun getCategories(collectionType: String): Single<List<CategoryEntity>> {
        return categoryDao.getCategories(collectionType)
    }

    override fun clearCategoriesByCollectionType(collectionType: String) {
        categoryDao.clearCategoriesByCollectionType(collectionType)
    }
}
