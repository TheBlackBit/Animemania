package com.theblackbit.animemania.android.data.external.repository

import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ChaptersResponse
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single

interface MangaChaptersByKitsuRepository {
    fun getMangaChapters(
        collectionId: String,
        pageLimit: String,
        pageOffset: String
    ): Single<SafeApiRequest.ApiResultHandle<ChaptersResponse>>
}
