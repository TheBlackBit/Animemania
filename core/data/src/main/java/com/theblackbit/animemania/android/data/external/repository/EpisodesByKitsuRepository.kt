package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.response.episodesresponse.EpisodesResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single

interface EpisodesByKitsuRepository {
    fun getCollectionEpisodes(
        collectionId: String,
        pageNumber: String,
        pageOffset: String
    ): Single<SafeApiRequest.ApiResultHandle<EpisodesResponse>>
}
