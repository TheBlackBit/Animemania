package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.categories.Categories
import com.theblackbit.animemania.android.data.external.datasource.categories.animeCategories
import io.reactivex.rxjava3.core.Single

// TODO: ADD UNIT TEST
class AnimeCategoriesRepository : CategoriesRemoteRepository {

    override fun getCategories(): Single<List<Categories>> {
        return Single.just(
            animeCategories,
        )
    }
}
