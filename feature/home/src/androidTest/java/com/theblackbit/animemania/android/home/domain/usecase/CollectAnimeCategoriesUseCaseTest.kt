package com.theblackbit.animemania.android.home.domain.usecase

import com.theblackbit.animemania.android.core.testing.data.collectionCategories
import com.theblackbit.animemania.android.domain.usecase.CollectCategoriesUseCase
import com.theblackbit.animemania.android.model.Category
import io.reactivex.rxjava3.core.Single

class CollectAnimeCategoriesUseCaseTest : CollectCategoriesUseCase {
    override fun collect(): Single<List<Category>> {
        return Single.just(
            collectionCategories,
        )
    }
}
