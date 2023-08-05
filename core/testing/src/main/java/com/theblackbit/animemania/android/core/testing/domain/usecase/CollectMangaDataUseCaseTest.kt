package com.theblackbit.animemania.android.core.testing.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.core.testing.data.CATEGORY_MOST_WANTED_ID
import com.theblackbit.animemania.android.core.testing.data.CATEGORY_TOP_RATED_ID
import com.theblackbit.animemania.android.core.testing.data.CATEGORY_TRENDING_ID
import com.theblackbit.animemania.android.core.testing.data.manga.mostWantedMangaData
import com.theblackbit.animemania.android.core.testing.data.manga.popularMangaData
import com.theblackbit.animemania.android.core.testing.data.manga.topRatedMangaData
import com.theblackbit.animemania.android.core.testing.data.manga.trendingMangaData
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Flowable
import java.util.concurrent.TimeUnit

class CollectMangaDataUseCaseTest : CollectCollectionDataUseCase {
    override fun collectByCategory(categoryId: Int): Flowable<PagingData<Collection>> {
        return when (categoryId) {
            CATEGORY_TRENDING_ID -> Flowable.just(PagingData.from(trendingMangaData))
                .delay(1, TimeUnit.SECONDS)

            CATEGORY_MOST_WANTED_ID -> Flowable.just(PagingData.from(mostWantedMangaData))
                .delay(2, TimeUnit.SECONDS)

            CATEGORY_TOP_RATED_ID -> Flowable.just(PagingData.from(topRatedMangaData))
                .delay(3, TimeUnit.SECONDS)

            else -> Flowable.just(PagingData.from(popularMangaData))
                .delay(4, TimeUnit.SECONDS)
        }
    }
}
