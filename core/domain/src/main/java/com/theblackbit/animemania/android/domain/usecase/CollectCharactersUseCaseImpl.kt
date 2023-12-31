package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.theblackbit.animemania.android.data.pagingsource.character.CharacterPagingSourceFactory
import com.theblackbit.animemania.android.model.Character
import io.reactivex.rxjava3.core.Flowable

class CollectCharactersUseCaseImpl(
    private val characterPagingSourceFactory: CharacterPagingSourceFactory,
    private val pagingConfig: PagingConfig
) : CollectCharactersUseCase {
    override fun collect(collectionId: String, mediaType: String): Flowable<PagingData<Character>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                characterPagingSourceFactory.getCharacterPagingSourceByMediaType(
                    collectionId,
                    mediaType
                )
            }
        ).flowable
    }
}
