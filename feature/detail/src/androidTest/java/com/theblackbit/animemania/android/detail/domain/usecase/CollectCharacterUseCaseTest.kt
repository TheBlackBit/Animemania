package com.theblackbit.animemania.android.detail.domain.usecase

import androidx.paging.PagingData
import com.theblackbit.animemania.android.core.testing.data.character.charactersData
import com.theblackbit.animemania.android.domain.usecase.CollectCharactersUseCase
import com.theblackbit.animemania.android.model.Character
import io.reactivex.rxjava3.core.Flowable
import java.util.concurrent.TimeUnit

class CollectCharacterUseCaseTest : CollectCharactersUseCase {
    override fun collect(collectionId: String): Flowable<PagingData<Character>> {
        return Flowable.just(PagingData.from(charactersData))
            .delay(1, TimeUnit.SECONDS)
    }
}
