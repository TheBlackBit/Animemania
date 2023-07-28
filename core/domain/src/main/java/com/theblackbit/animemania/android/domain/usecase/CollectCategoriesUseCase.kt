package com.theblackbit.animemania.android.domain.usecase

import com.theblackbit.animemania.android.model.Category
import io.reactivex.rxjava3.core.Single

interface CollectCategoriesUseCase {
    fun collect(): Single<List<Category>>
}
