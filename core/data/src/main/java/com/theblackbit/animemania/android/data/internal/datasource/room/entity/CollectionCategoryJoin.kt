package com.theblackbit.animemania.android.data.internal.datasource.room.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CollectionCategoryJoin(
    @Embedded
    val collectionCategoryJoinEntity: CollectionCategoryJoinEntity,
    @Relation(
        parentColumn = "collectionId",
        entityColumn = "categoryId",
        associateBy = Junction(CollectionCategoryJoinEntity::class),
    )
    val collections: List<CollectionEntity>,
)
