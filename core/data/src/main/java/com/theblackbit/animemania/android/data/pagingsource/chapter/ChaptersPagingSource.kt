package com.theblackbit.animemania.android.data.pagingsource.chapter

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.ChaptersResponse
import com.theblackbit.animemania.android.data.external.datasource.response.chaptersresponse.toChapterEntity
import com.theblackbit.animemania.android.data.external.repository.MangaChaptersByKitsuRepository
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.ChapterEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.toChapterModel
import com.theblackbit.animemania.android.data.internal.repository.ChapterLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.collection.anime.AnimePagingSourceFactory
import com.theblackbit.animemania.android.model.Chapter
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

// TODO: ADD UNIT TEST

class ChaptersPagingSource(
    private val localRepository: ChapterLocalRepository,
    private val remoteRepository: MangaChaptersByKitsuRepository,
    private val collectionId: Int,
) : RxPagingSource<Int, Chapter>() {

    companion object {
        const val PAGE_LIMIT = 20
    }

    override fun getRefreshKey(state: PagingState<Int, Chapter>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Chapter>> {
        val currentPage = params.key ?: 1
        val pageOffset = validPageOffset(currentPage)

        return remoteRepository
            .getMangaChapters(
                collectionId.toString(),
                PAGE_LIMIT.toString(),
                pageOffset.toString(),
            )
            .subscribeOn(Schedulers.io())
            .concatMap { result ->
                handleApiResult(currentPage = currentPage, result = result)
                localRepository.getChaptersByCollection(collectionId, currentPage)
            }
            .map { characterEntities -> toLoadResult(characterEntities, currentPage) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun validPageOffset(currentPage: Int): String? {
        return if (currentPage == 1) null else ((currentPage - 1) * AnimePagingSourceFactory.PAGE_LIMIT).toString()
    }

    private fun handleApiResult(
        currentPage: Int,
        result: SafeApiRequest.ApiResultHandle<ChaptersResponse>,
    ) {
        if (result is SafeApiRequest.ApiResultHandle.Success) {
            if (currentPage == 1) {
                localRepository.deleteChaptersByCollection(collectionId)
            }
            val chapterEntities = result.value.chapterData
                .map { it.toChapterEntity(page = currentPage, collectionId = collectionId) }

            localRepository.insertChapters(chapterEntities)
        }
    }

    private fun toLoadResult(
        characterEntity: List<ChapterEntity>,
        currentPage: Int,
    ): LoadResult<Int, Chapter> {
        val chapters = characterEntity.map { it.toChapterModel() }
        return LoadResult.Page(
            data = chapters,
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = if (chapters.isEmpty()) null else currentPage + 1,
        )
    }
}
