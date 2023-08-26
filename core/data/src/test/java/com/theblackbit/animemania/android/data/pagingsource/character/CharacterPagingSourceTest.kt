package com.theblackbit.animemania.android.data.pagingsource.character

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.testing.TestPager
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterAttributes
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterData
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.CharacterResponse
import com.theblackbit.animemania.android.data.external.datasource.response.charactersresponse.toCharacterEntity
import com.theblackbit.animemania.android.data.external.repository.CharacterByKitsuRepository
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.toCharacterModel
import com.theblackbit.animemania.android.data.internal.repository.CharacterLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.chapter.ChapterPagingSourceFactory
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CharacterPagingSourceTest {

    private lateinit var sut: CharacterPagingSource

    @Mock
    private lateinit var localRepository: CharacterLocalRepository

    @Mock
    private lateinit var remoteRepository: CharacterByKitsuRepository

    private val mediaType = "Anime"

    private val collectionId = "1"

    private val response by lazy {
        fakeResponse()
    }

    @Test
    fun testRefreshKey() = runTest {
        sut = CharacterPagingSource(
            localRepository = localRepository,
            remoteRepository = remoteRepository,
            mediaType = mediaType,
            collectionId = collectionId
        )

        val result = sut.getRefreshKey(
            PagingState(
                pages = emptyList(),
                anchorPosition = 1,
                config = PagingConfig(
                    pageSize = 10
                ),
                leadingPlaceholderCount = 0
            )
        )

        Assert.assertEquals(result, 1)
    }

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runTest {
        Mockito.`when`(
            /* methodCall = */ remoteRepository.getCollectionCharacters(
                collectionId = collectionId,
                mediaType = mediaType,
                pageNumber = CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT.toString(),
                pageOffset = null
            )
        ).thenReturn(
            Single.just(SafeApiRequest.ApiResultHandle.Success(response))
        )

        val characterEntities = response.characterData.map {
            it.toCharacterEntity(page = 1, collectionId = collectionId)
        }

        val characterModel = characterEntities.map {
            it.toCharacterModel()
        }

        sut = CharacterPagingSource(
            localRepository = localRepository,
            remoteRepository = remoteRepository,
            mediaType = mediaType,
            collectionId = collectionId
        )

        Mockito.`when`(
            localRepository.getCharactersByCollectionId(
                collectionId,
                page = 1
            )
        ).thenReturn(
            Single.just(
                characterEntities
            )
        )

        val pager = TestPager(
            config = PagingConfig(
                pageSize = CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT
            ),
            pagingSource = sut
        )

        val page = with(pager) {
            refresh()
        } as PagingSource.LoadResult.Page

        Mockito.verify(localRepository).deleteCharactersByCollectionId(collectionId)

        Mockito.verify(localRepository).insertCharacters(characterEntities)

        assert(page.data.containsAll(characterModel))
    }

    @Test
    fun whenLoadSuccessPage2DoesNotClearCache() = runTest {
        val pageNumber = 2

        Mockito.`when`(
            /* methodCall = */ remoteRepository.getCollectionCharacters(
                collectionId = collectionId,
                mediaType = mediaType,
                pageNumber = CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT.toString(),
                pageOffset = CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT.toString()
            )
        ).thenReturn(
            Single.just(SafeApiRequest.ApiResultHandle.Success(response))
        )

        val characterEntities = response.characterData.map {
            it.toCharacterEntity(page = 1, collectionId = collectionId)
        }

        val characterModel = characterEntities.map {
            it.toCharacterModel()
        }

        sut = CharacterPagingSource(
            localRepository = localRepository,
            remoteRepository = remoteRepository,
            mediaType = mediaType,
            collectionId = collectionId
        )

        Mockito.`when`(
            localRepository.getCharactersByCollectionId(
                collectionId,
                page = pageNumber
            )
        ).thenReturn(
            Single.just(
                characterEntities
            )
        )

        val pager = TestPager(
            config = PagingConfig(
                pageSize = CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT
            ),
            pagingSource = sut
        )

        val page = with(pager) {
            refresh(initialKey = pageNumber)
        } as PagingSource.LoadResult.Page

        Mockito.verify(localRepository, Mockito.times(0))
            .deleteCharactersByCollectionId(collectionId)

        Mockito.verify(localRepository).insertCharacters(characterEntities)

        assert(page.data.containsAll(characterModel))
    }

    @Test
    fun whenApiLoadErrorReturnsDataFromCache() = runTest {
        val pageNumber = 1

        Mockito.`when`(
            /* methodCall = */ remoteRepository.getCollectionCharacters(
                collectionId = collectionId,
                mediaType = mediaType,
                pageNumber = CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT.toString(),
                pageOffset = null
            )
        ).thenReturn(
            Single.just(SafeApiRequest.ApiResultHandle.NetworkError)
        )

        val characterEntities = response.characterData.map {
            it.toCharacterEntity(page = pageNumber, collectionId = collectionId)
        }

        val characterModels = characterEntities.map {
            it.toCharacterModel()
        }

        sut = CharacterPagingSource(
            localRepository = localRepository,
            remoteRepository = remoteRepository,
            mediaType = mediaType,
            collectionId = collectionId
        )

        Mockito.`when`(
            localRepository.getCharactersByCollectionId(collectionId, pageNumber)
        ).thenReturn(
            Single.just(
                characterEntities
            )
        )

        val pager = TestPager(
            config = PagingConfig(
                pageSize = ChapterPagingSourceFactory.CHAPTER_PAGE_LIMIT
            ),
            pagingSource = sut
        )

        val page = with(pager) {
            refresh(initialKey = pageNumber)
        } as PagingSource.LoadResult.Page

        Mockito.verify(localRepository, Mockito.times(0))
            .deleteCharactersByCollectionId(collectionId)

        Mockito.verify(localRepository, Mockito.times(0))
            .insertCharacters(characterEntities)

        assert(page.data.containsAll(characterModels))
    }

    @Test
    fun testApiAndCacheReturnsEmptyData() = runTest {
        val pageNumber = 1

        Mockito.`when`(
            /* methodCall = */ remoteRepository.getCollectionCharacters(
                collectionId = collectionId,
                mediaType = mediaType,
                pageNumber = CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT.toString(),
                pageOffset = null
            )
        ).thenReturn(
            Single.just(
                SafeApiRequest.ApiResultHandle.ApiError
            )
        )

        sut = CharacterPagingSource(
            localRepository = localRepository,
            remoteRepository = remoteRepository,
            mediaType = mediaType,
            collectionId = collectionId
        )

        Mockito.`when`(
            localRepository.getCharactersByCollectionId(collectionId, pageNumber)
        ).thenReturn(
            Single.just(
                emptyList()
            )
        )

        val pager = TestPager(
            config = PagingConfig(
                pageSize = CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT
            ),
            pagingSource = sut
        )

        val page = with(pager) {
            refresh(initialKey = pageNumber)
        } as PagingSource.LoadResult.Page

        assert(page.data.isEmpty())
        Assert.assertEquals(page.nextKey, null)
    }

    private fun fakeResponse(): CharacterResponse {
        val characters: ArrayList<CharacterData> = ArrayList()
        for (i in 0 until CharacterPagingSourceFactory.CHARACTER_PAGE_LIMIT) {
            CharacterData(
                id = i.toString(),
                attributes = CharacterAttributes(
                    description = "TestDescription",
                    image = null,
                    canonicalName = "Test"
                )
            )
        }
        return CharacterResponse(characters)
    }
}
