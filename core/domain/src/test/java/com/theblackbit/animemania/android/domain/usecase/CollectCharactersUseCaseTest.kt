package com.theblackbit.animemania.android.domain.usecase

import androidx.paging.PagingConfig
import com.theblackbit.animemania.android.data.pagingsource.character.CharacterPagingSource
import com.theblackbit.animemania.android.data.pagingsource.character.CharacterPagingSourceFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CollectCharactersUseCaseTest {

    @Mock
    private lateinit var pagingSourceFactory: CharacterPagingSourceFactory

    @Mock
    private lateinit var pagingConfig: PagingConfig

    private lateinit var collectCharactersUseCaseImpl: CollectCharactersUseCaseImpl

    @Mock
    private lateinit var characterPagingSource: CharacterPagingSource

    @Before
    fun setup() {
        collectCharactersUseCaseImpl =
            CollectCharactersUseCaseImpl(pagingSourceFactory, pagingConfig)

        Mockito.`when`(
            pagingSourceFactory.getCharacterPagingSourceByMediaType(
                collectionId = "1",
                mediaType = "Anime"
            )
        )
            .thenReturn(characterPagingSource)

        Mockito.`when`(characterPagingSource.jumpingSupported)
            .thenReturn(true)
    }

    @Test
    fun `test collectByCategory`() {
        collectCharactersUseCaseImpl.collect(
            collectionId = "1",
            mediaType = "Anime"
        )
            .subscribe()

        Mockito.verify(pagingSourceFactory).getCharacterPagingSourceByMediaType(
            collectionId = "1",
            mediaType = "Anime"
        )
    }
}
