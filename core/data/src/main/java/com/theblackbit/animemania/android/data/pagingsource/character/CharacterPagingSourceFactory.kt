package com.theblackbit.animemania.android.data.pagingsource.character

import androidx.paging.rxjava3.RxPagingSource
import com.theblackbit.animemania.android.data.external.repository.CharacterByKitsuRepository
import com.theblackbit.animemania.android.data.internal.repository.CharacterLocalRepository
import com.theblackbit.animemania.android.model.Character

class CharacterPagingSourceFactory(
    private val localRepository: CharacterLocalRepository,
    private val remoteRepository: CharacterByKitsuRepository,
) {

    companion object {
        const val CHARACTER_PAGE_LIMIT = 20
    }

    fun getCharacterPagingSourceByMediaType(
        collectionId: String,
        mediaType: String,
    ): RxPagingSource<Int, Character> {
        return CharacterPagingSource(
            localRepository = localRepository,
            remoteRepository = remoteRepository,
            mediaType = mediaType,
            collectionId = collectionId,
        )
    }
}
