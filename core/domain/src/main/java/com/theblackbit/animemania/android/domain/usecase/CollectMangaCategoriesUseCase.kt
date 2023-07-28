package com.theblackbit.animemania.android.domain.usecase

import com.theblackbit.animemania.android.model.Category
import io.reactivex.rxjava3.core.Single

class CollectMangaCategoriesUseCase : CollectCategoriesUseCase {
    override fun collect(): Single<List<Category>> {
        return Single.just(emptyList())
    }
}
