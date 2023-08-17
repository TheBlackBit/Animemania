package com.theblackbit.animemania.android.detail.mockrepository

import android.content.Context
import com.theblackbit.animemania.android.core.testing.helpers.jsonFileToKotlinClass
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodesResponse
import com.theblackbit.animemania.android.data.external.repository.EpisodesByKitsuRepository
import com.theblackbit.animemania.android.data.pagingsource.chapter.ChapterPagingSourceFactory
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

const val VALID_COLLECTION_ANIME_ID = "12"

class AnimeEpisodesByKitsuRepositoryMock(private val context: Context) : EpisodesByKitsuRepository {
    override fun getCollectionEpisodes(
        collectionId: String,
        pageNumber: String,
        pageOffset: String,
    ): Single<SafeApiRequest.ApiResultHandle<EpisodesResponse>> {
        val delay = if (pageOffset == "null") 1L else 0
        val response = when {
            (pageOffset == "null" && collectionId == VALID_COLLECTION_ANIME_ID) -> jsonFileToKotlinClass(
                context,
                "onepiece_anime_episodes_response_first_page_test.json",
            )

            (pageOffset == ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT.toString() && collectionId == VALID_COLLECTION_ANIME_ID) -> jsonFileToKotlinClass(
                context,
                "onepiece_anime_episodes_response_second_page_test.json",
            )

            else -> EpisodesResponse(emptyList())
        }

        return Single.just<SafeApiRequest.ApiResultHandle<EpisodesResponse>>(
            SafeApiRequest.ApiResultHandle.Success(
                response,
            ),
        ).delay(delay, TimeUnit.SECONDS)
    }
}
