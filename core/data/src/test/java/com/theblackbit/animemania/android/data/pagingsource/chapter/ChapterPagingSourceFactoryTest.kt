package com.theblackbit.animemania.android.data.pagingsource.chapter

import com.theblackbit.animemania.android.data.external.repository.EpisodesByKitsuRepository
import com.theblackbit.animemania.android.data.external.repository.MangaChaptersByKitsuRepository
import com.theblackbit.animemania.android.data.internal.repository.ChapterLocalRepository
import com.theblackbit.animemania.android.model.CollectionType
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChapterPagingSourceFactoryTest {

    private lateinit var sut: ChapterPagingSourceFactory

    @Mock
    private lateinit var episodesByKitsuRepository: EpisodesByKitsuRepository

    @Mock
    private lateinit var chaptersByKitsuRepository: MangaChaptersByKitsuRepository

    @Mock
    private lateinit var chapterLocalRepository: ChapterLocalRepository

    @Before
    fun setUp() {
        sut = ChapterPagingSourceFactory(
            localRepository = chapterLocalRepository,
            mangaChapters = chaptersByKitsuRepository,
            animeEpisodes = episodesByKitsuRepository
        )
    }

    @Test
    fun whenRequestAnimeEpisodesReturnsEpisodePagingSource() {
        val result = sut.getChapterPagingSourceCollectionId(
            collectionId = 1.toString(),
            collectionType = CollectionType.ANIME

        )

        assert(result is EpisodePagingSource)
    }

    @Test
    fun whenRequestMangaChaptersReturnsChaptersPagingSource() {
        val result = sut.getChapterPagingSourceCollectionId(
            collectionId = 1.toString(),
            collectionType = CollectionType.MANGA

        )

        assert(result is ChaptersPagingSource)
    }
}
