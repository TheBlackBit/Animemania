package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuEpisodesDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodesResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single

class AnimeEpisodesByKitsuRepository(
    private val kitsuEpisodesDataSource: KitsuEpisodesDataSource,
    private val safeApiRequest: SafeApiRequest,
) : EpisodesByKitsuRepository {
    override fun getCollectionEpisodes(
        collectionId: String,
        pageNumber: String,
        pageOffset: String,
    ): Single<SafeApiRequest.ApiResultHandle<EpisodesResponse>> {
        return safeApiRequest.request {
            kitsuEpisodesDataSource.getEpisodes(
                collectionId = collectionId,
                pageNumber = pageNumber,
                pageOffset = pageOffset,
            ).map { it }
        }
    }
}
