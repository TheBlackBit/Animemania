package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuChaptersDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ChaptersResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single

class MangaChaptersByKitsuRepositoryImpl(
    private val kitsuChaptersDataSource: KitsuChaptersDataSource,
    private val safeApiRequest: SafeApiRequest
) : MangaChaptersByKitsuRepository {
    override fun getMangaChapters(
        collectionId: String,
        pageLimit: String,
        pageOffset: String
    ): Single<SafeApiRequest.ApiResultHandle<ChaptersResponse>> {
        return safeApiRequest.request {
            kitsuChaptersDataSource.getChapters(
                mangaId = collectionId,
                pageLimit = pageLimit,
                pageOffset = pageOffset
            ).map { it }
        }
    }
}
