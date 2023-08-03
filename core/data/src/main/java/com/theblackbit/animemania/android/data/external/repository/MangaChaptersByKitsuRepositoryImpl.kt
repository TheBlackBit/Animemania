package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.kitsuapi.KitsuChaptersDataSource
import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ChaptersResponse
import io.reactivex.rxjava3.core.Single

class MangaChaptersByKitsuRepositoryImpl(
    private val kitsuChaptersDataSource: KitsuChaptersDataSource,
) : MangaChaptersByKitsuRepository {
    override fun getMangaChapters(
        collectionId: String,
        pageNumber: String,
        pageOffset: String,
    ): Single<ChaptersResponse> {
        return kitsuChaptersDataSource.getChapters(
            mangaId = collectionId,
            pageNumber = pageNumber,
            pageOffset = pageOffset,
        )
    }
}
