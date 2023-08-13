package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.categories.CategoryData
import com.theblackbit.animemania.android.data.external.datasource.categories.animeCategories
import io.reactivex.rxjava3.core.Single

class AnimeCategoriesRepository : CategoriesRemoteRepository {

    override fun getCategories(): Single<List<CategoryData>> {
        return Single.just(
            animeCategories,
        )
    }
}
