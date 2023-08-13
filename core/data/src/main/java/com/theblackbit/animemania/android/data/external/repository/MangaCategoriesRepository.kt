package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.categories.CategoryData
import com.theblackbit.animemania.android.data.external.datasource.categories.mangaCategories
import io.reactivex.rxjava3.core.Single

class MangaCategoriesRepository : CategoriesRemoteRepository {

    override fun getCategories(): Single<List<CategoryData>> {
        return Single.just(
            mangaCategories,
        )
    }
}
