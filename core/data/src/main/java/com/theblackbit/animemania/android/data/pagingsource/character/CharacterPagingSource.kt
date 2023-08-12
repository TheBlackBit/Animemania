package com.theblackbit.animemania.android.data.pagingsource.character

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterResponse
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.toCharacterEntity
import com.theblackbit.animemania.android.data.external.repository.CharacterByKitsuRepository
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CharacterEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.toCharacterModel
import com.theblackbit.animemania.android.data.internal.repository.CharacterLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.character.CharacterPagingSourceFactory.Companion.CHARACTER_PAGE_LIMIT
import com.theblackbit.animemania.android.model.Character
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

// TODO: ADD UNIT TEST
class CharacterPagingSource(
    private val localRepository: CharacterLocalRepository,
    private val remoteRepository: CharacterByKitsuRepository,
    private val mediaType: String,
    private val collectionId: String,
) : RxPagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Character>> {
        val currentPage = params.key ?: 1
        val pageOffset = validPageOffset(currentPage)

        return remoteRepository
            .getCollectionCharacters(
                mediaType,
                collectionId,
                CHARACTER_PAGE_LIMIT.toString(),
                pageOffset,
            )
            .subscribeOn(Schedulers.io())
            .concatMap { result ->
                handleApiResult(currentPage = currentPage, result = result)
                localRepository.getCharactersByCollectionId(collectionId, currentPage)
            }
            .map { characterEntities -> toLoadResult(characterEntities, currentPage) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun handleApiResult(
        currentPage: Int,
        result: SafeApiRequest.ApiResultHandle<CharacterResponse>,
    ) {
        if (result is SafeApiRequest.ApiResultHandle.Success) {
            if (currentPage == 1) {
                localRepository.deleteCharactersByCollectionId(collectionId)
            }
            val characterEntities = result.value.characterData
                .map { it.toCharacterEntity(page = currentPage, collectionId = collectionId) }
            localRepository.insertCharacters(characterEntities)
        }
    }

    private fun validPageOffset(currentPage: Int): String? {
        return if (currentPage == 1) null else ((currentPage - 1) * CHARACTER_PAGE_LIMIT).toString()
    }

    private fun toLoadResult(
        characterEntity: List<CharacterEntity>,
        currentPage: Int,
    ): LoadResult<Int, Character> {
        val characters = characterEntity.map { it.toCharacterModel() }
        return LoadResult.Page(
            data = characters,
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = if (characters.isEmpty()) null else currentPage + 1,
        )
    }
}
