package com.theblackbit.animemania.android.core.testing.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.core.testing.data.chapter.chaptersData
import com.theblackbit.animemania.android.domain.usecase.CollectChaptersUseCase
import com.theblackbit.animemania.android.model.Chapter
import io.reactivex.rxjava3.core.Flowable
import java.util.concurrent.TimeUnit

const val ID_COLLECTION_DATA = "44081"

class CollectChaptersUseCaseTest : CollectChaptersUseCase {
    override fun collect(collectionId: String): Flowable<PagingData<Chapter>> {
        return if (collectionId == ID_COLLECTION_DATA) {
            Flowable.just(PagingData.from(chaptersData))
                .delay(1, TimeUnit.SECONDS)
        } else {
            Flowable.just(PagingData.empty())
        }
    }
}
