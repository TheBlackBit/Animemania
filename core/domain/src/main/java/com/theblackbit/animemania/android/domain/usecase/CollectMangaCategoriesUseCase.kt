package com.theblackbit.animemania.android.domain.usecase

import com.theblackbit.animemania.android.data.external.datasource.categories.toCategory
import com.theblackbit.animemania.android.data.external.repository.CategoriesRemoteRepository
import com.theblackbit.animemania.android.model.Category
import io.reactivex.rxjava3.core.Single

// TODO: ADD UNIT TEST
class CollectMangaCategoriesUseCase(
    private val mangaCategoriesRepository: CategoriesRemoteRepository,
) : CollectCategoriesUseCase {
    override fun collect(): Single<List<Category>> {
        return mangaCategoriesRepository.getCategories()
            .map { categories ->
                categories.map { categoryData ->
                    categoryData.toCategory()
                }
            }
    }
}
