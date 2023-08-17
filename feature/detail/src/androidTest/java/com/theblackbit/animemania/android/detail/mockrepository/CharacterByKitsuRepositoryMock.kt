package com.theblackbit.animemania.android.detail.mockrepository

import android.content.Context
import com.theblackbit.animemania.android.core.testing.helpers.jsonFileToKotlinClass
import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuMediaType
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterResponse
import com.theblackbit.animemania.android.data.external.repository.CharacterByKitsuRepository
import com.theblackbit.animemania.android.data.pagingsource.character.CharacterPagingSourceFactory
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class CharacterByKitsuRepositoryMock(private val context: Context) :
    CharacterByKitsuRepository {
    override fun getCollectionCharacters(
        mediaType: String,
        collectionId: String,
        pageNumber: String,
        pageOffset: String?,
    ): Single<SafeApiRequest.ApiResultHandle<CharacterResponse>> {
        return when (mediaType) {
            KitsuMediaType.ANIME_MEDIA_TYPE -> animeCharacters(pageOffset, collectionId)
            else -> mangaCharacters(pageOffset, collectionId)
        }
    }

    private fun animeCharacters(
        pageOffset: String?,
        collectionId: String,
    ): Single<SafeApiRequest.ApiResultHandle<CharacterResponse>> {
        val delay = if (pageOffset == null) 1L else 0
        val response = when {
            (pageOffset == null && collectionId == VALID_COLLECTION_ANIME_ID) -> jsonFileToKotlinClass(
                context,
                "onepiece_anime_characters_first_page_test.json",
            )

            (pageOffset == CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT.toString() && collectionId == VALID_COLLECTION_ANIME_ID) -> jsonFileToKotlinClass(
                context,
                "onepiece_anime_characters_second_page_test.json",
            )

            else -> CharacterResponse(emptyList())
        }

        return Single.just<SafeApiRequest.ApiResultHandle<CharacterResponse>>(
            SafeApiRequest.ApiResultHandle.Success(
                response,
            ),
        ).delay(delay, TimeUnit.SECONDS)
    }

    private fun mangaCharacters(
        pageOffset: String?,
        collectionId: String,
    ): Single<SafeApiRequest.ApiResultHandle<CharacterResponse>> {
        val delay = if (pageOffset == null) 1L else 0
        val response = when {
            (pageOffset == null && collectionId == VALID_COLLECTION_ID_MANGA) -> jsonFileToKotlinClass(
                context,
                "berserk_manga_characters_response_first_page_test.json",
            )

            (pageOffset == CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT.toString() && collectionId == VALID_COLLECTION_ANIME_ID) -> jsonFileToKotlinClass(
                context,
                "berserk_manga_characters_response_second_page.json",
            )

            else -> CharacterResponse(emptyList())
        }

        return Single.just<SafeApiRequest.ApiResultHandle<CharacterResponse>>(
            SafeApiRequest.ApiResultHandle.Success(
                response,
            ),
        ).delay(delay, TimeUnit.SECONDS)
    }
}
