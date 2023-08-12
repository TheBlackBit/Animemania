package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.categories.Categories
import io.reactivex.rxjava3.core.Single

interface CategoriesRemoteRepository {
    fun getCategories(): Single<List<Categories>>
}
