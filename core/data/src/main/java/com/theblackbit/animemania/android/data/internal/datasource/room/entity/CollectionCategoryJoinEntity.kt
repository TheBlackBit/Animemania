package com.theblackbit.animemania.android.data.internal.datasource.room.entity

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["collectionId", "categoryId"], indices = [Index(value = ["categoryId"])])
data class CollectionCategoryJoinEntity(
    val collectionId: String,
    val categoryId: Int,
    val pageNumber: Int,
)
