package com.theblackbit.animemania.android.domain.usecase

import com.theblackbit.animemania.android.data.external.datasource.categories.toCategory
import com.theblackbit.animemania.android.data.external.repository.CategoriesRemoteRepository
import com.theblackbit.animemania.android.model.Category
import io.reactivex.rxjava3.core.Single

// TODO: ADD UNIT TEST
class CollectAnimeCategoriesUseCase(
    private val animeCategoryRepository: CategoriesRemoteRepository,
) : CollectCategoriesUseCase {
    override fun collect(): Single<List<Category>> {
        return animeCategoryRepository.getCategories()
            .map { categories ->
                categories.map { categoryData ->
                    categoryData.toCategory()
                }
            }
    }
}
