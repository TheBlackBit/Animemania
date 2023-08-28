package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingConfig
import com.theblackbit.animemania.android.data.pagingsource.chapter.ChapterPagingSourceFactory
import com.theblackbit.animemania.android.data.pagingsource.chapter.ChaptersPagingSource
import com.theblackbit.animemania.android.model.CollectionType
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CollectChaptersUseCaseTest {

    @Mock
    private lateinit var pagingSourceFactory: ChapterPagingSourceFactory

    @Mock
    private lateinit var pagingConfig: PagingConfig

    private lateinit var collectChaptersUseCaseImpl: CollectChaptersUseCaseImpl

    @Mock
    private lateinit var chaptersPagingSource: ChaptersPagingSource

    @Before
    fun setup() {
        collectChaptersUseCaseImpl =
            CollectChaptersUseCaseImpl(pagingSourceFactory, pagingConfig)

        Mockito.`when`(
            pagingSourceFactory.getChapterPagingSourceCollectionId(
                collectionId = "1",
                collectionType = CollectionType.ANIME
            )
        )
            .thenReturn(chaptersPagingSource)

        Mockito.`when`(chaptersPagingSource.jumpingSupported)
            .thenReturn(true)
    }

    @Test
    fun `test collectByCategory`() {
        collectChaptersUseCaseImpl.collect(
            collectionId = "1",
            collectionType = CollectionType.ANIME
        )
            .subscribe()

        Mockito.verify(pagingSourceFactory).getChapterPagingSourceCollectionId(
            collectionId = "1",
            collectionType = CollectionType.ANIME
        )
    }
}
