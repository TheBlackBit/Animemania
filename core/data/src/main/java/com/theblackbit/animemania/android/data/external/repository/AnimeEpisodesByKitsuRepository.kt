package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuEpisodesDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodesResponse
import io.reactivex.rxjava3.core.Single

class AnimeEpisodesByKitsuRepository(
    private val kitsuEpisodesDataSource: KitsuEpisodesDataSource,
) : EpisodesByKitsuRepository {
    override fun getCollectionEpisodes(
        collectionId: String,
        pageNumber: String,
        pageOffset: String,
    ): Single<EpisodesResponse> {
        return kitsuEpisodesDataSource.getEpisodes(
            collectionId = collectionId,
            pageNumber = pageNumber,
            pageOffset = pageOffset,
        )
    }
}
