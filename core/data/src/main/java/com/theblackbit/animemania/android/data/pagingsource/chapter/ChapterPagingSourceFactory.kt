package com.theblackbit.animemania.android.data.pagingsource.chapter

import androidx.paging.rxjava3.RxPagingSource
import com.theblackbit.animemania.android.data.external.repository.EpisodesByKitsuRepository
import com.theblackbit.animemania.android.data.external.repository.MangaChaptersByKitsuRepository
import com.theblackbit.animemania.android.data.internal.repository.ChapterLocalRepository
import com.theblackbit.animemania.android.model.Chapter
import com.theblackbit.animemania.android.model.CollectionType

class ChapterPagingSourceFactory(
    private val localRepository: ChapterLocalRepository,
    private val mangaChapters: MangaChaptersByKitsuRepository,
    private val animeEpisodes: EpisodesByKitsuRepository,
) {

    companion object {
        const val CHAPTER_PAGE_LIMIT = 20
    }

    fun getChapterPagingSourceCollectionId(
        collectionId: String,
        collectionType: CollectionType,
    ): RxPagingSource<Int, Chapter> {
        return when (collectionType) {
            CollectionType.ANIME -> EpisodePagingSource(
                localRepository = localRepository,
                remoteRepository = animeEpisodes,
                collectionId = collectionId,
            )

            CollectionType.MANGA -> ChaptersPagingSource(
                localRepository = localRepository,
                remoteRepository = mangaChapters,
                collectionId = collectionId,
            )
        }
    }
}
