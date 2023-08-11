package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.categories.Categories
import com.theblackbit.animemania.android.data.external.datasource.categories.mangaCategories
import io.reactivex.rxjava3.core.Single

// TODO: ADD UNIT TEST
class MangaCategoriesRepository : CategoriesRemoteRepository {

    override fun getCategoriesByCollectionType(): Single<List<Categories>> {
        return Single.just(
            mangaCategories,
        )
    }
}
