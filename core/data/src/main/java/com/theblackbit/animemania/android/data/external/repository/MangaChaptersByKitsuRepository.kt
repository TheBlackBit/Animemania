package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ChaptersResponse
import io.reactivex.rxjava3.core.Single

interface MangaChaptersByKitsuRepository {
    fun getMangaChapters(
        collectionId: String,
        pageNumber: String,
        pageOffset: String,
    ): Single<ChaptersResponse>
}
