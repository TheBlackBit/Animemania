package com.theblackbit.animemania.android.detail.mockrepository

import android.content.Context
import com.theblackbit.animemania.android.core.testing.helpers.jsonFileToKotlinClass
import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ChaptersResponse
import com.theblackbit.animemania.android.data.external.repository.MangaChaptersByKitsuRepository
import com.theblackbit.animemania.android.data.pagingsource.chapter.ChapterPagingSourceFactory
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

const val VALID_COLLECTION_ID_MANGA = "8"

class MangaChaptersByKitsuRepositoryMock(private val context: Context) :
    MangaChaptersByKitsuRepository {
    override fun getMangaChapters(
        collectionId: String,
        pageLimit: String,
        pageOffset: String
    ): Single<SafeApiRequest.ApiResultHandle<ChaptersResponse>> {
        val delay = if (pageOffset == "null") 1L else 0
        val response = when {
            (pageOffset == "null" && collectionId == VALID_COLLECTION_ID_MANGA) -> jsonFileToKotlinClass(
                context,
                "berserk_manga_chapters_response_first_page_test.json"
            )

            (pageOffset == ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT.toString() && collectionId == VALID_COLLECTION_ID_MANGA) -> jsonFileToKotlinClass(
                context,
                "berserk_manga_chapters_response_second_page_test.json"
            )

            else -> ChaptersResponse(emptyList())
        }

        return Single.just<SafeApiRequest.ApiResultHandle<ChaptersResponse>>(
            SafeApiRequest.ApiResultHandle.Success(
                response
            )
        ).delay(delay, TimeUnit.SECONDS)
    }
}
