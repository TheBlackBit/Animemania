package com.theblackbit.animemania.android.core.testing.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.core.testing.data.CATEGORY_MOST_WANTED_ID
import com.theblackbit.animemania.android.core.testing.data.CATEGORY_TOP_RATED_ID
import com.theblackbit.animemania.android.core.testing.data.CATEGORY_TRENDING_ID
import com.theblackbit.animemania.android.core.testing.data.anime.mostWantedAnimeData
import com.theblackbit.animemania.android.core.testing.data.anime.popularAnimeData
import com.theblackbit.animemania.android.core.testing.data.anime.topRatedAnimeData
import com.theblackbit.animemania.android.core.testing.data.anime.trendingAnimeData
import com.theblackbit.animemania.android.domain.usecase.CollectCollectionDataUseCase
import com.theblackbit.animemania.android.model.Collection
import io.reactivex.rxjava3.core.Flowable
import java.util.concurrent.TimeUnit

class CollectAnimeDataUseCaseTest : CollectCollectionDataUseCase {
    override fun collectByCategory(categoryId: Int): Flowable<PagingData<Collection>> {
        return when (categoryId) {
            CATEGORY_TRENDING_ID -> Flowable.just(PagingData.from(trendingAnimeData))
                .delay(1, TimeUnit.SECONDS)

            CATEGORY_MOST_WANTED_ID -> Flowable.just(PagingData.from(mostWantedAnimeData))
                .delay(2, TimeUnit.SECONDS)

            CATEGORY_TOP_RATED_ID -> Flowable.just(PagingData.from(topRatedAnimeData))
                .delay(3, TimeUnit.SECONDS)

            else -> Flowable.just(PagingData.from(popularAnimeData))
                .delay(4, TimeUnit.SECONDS)
        }
    }
}
