package com.theblackbit.animemania.android.data.pagingsource.collection

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.toCollectionEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.CollectionEntity
import com.theblackbit.animemania.android.data.internal.datasource.room.entity.toCollection
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.model.CollectionType
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

open class CollectionPagingSource(
    private val localRepository: CollectionLocalRepository,
    private val request: (pageLimit: String, pageOffset: String?) -> Single<SafeApiRequest.ApiResultHandle<CollectionResponse>>,
    private val requestType: RequestType,
    private val collectionType: CollectionType,
) : RxPagingSource<Int, Collection>() {
    companion object {
        const val COLLECTION_PAGE_LIMIT = 20
    }

    override fun getRefreshKey(state: PagingState<Int, Collection>): Int? {
        return state.anchorPosition
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Collection>> {
        val currentPage = params.key ?: 1
        val pageOffset = validPageOffset(currentPage)

        return request
            .invoke(
                COLLECTION_PAGE_LIMIT.toString(),
                pageOffset,
            )
            .subscribeOn(Schedulers.io())
            .concatMap { result ->
                handleApiResult(currentPage = currentPage, result = result)
                localRepository.collectPagedCollections(
                    pageNumber = currentPage,
                    requestType = requestType,
                )
            }
            .map { collectionsCached ->
                val collections = collectionsCached.map { collectionCached ->
                    collectionCached.toCollection(collectionType)
                }
                toLoadResult(collections, currentPage)
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    private fun handleApiResult(
        currentPage: Int,
        result: SafeApiRequest.ApiResultHandle<CollectionResponse>,
    ) {
        if (result is SafeApiRequest.ApiResultHandle.Success) {
            handleApiResultSuccess(currentPage, result.value)
        }
    }

    private fun handleApiResultSuccess(currentPage: Int, collectionResponse: CollectionResponse) {
        if (currentPage == 1) {
            clearCollectionsByRequestType()
        }
        val entities = getEntitiesByDataResponse(collectionResponse, currentPage)

        localRepository.insertCollectionsEntities(entities)
    }

    private fun validPageOffset(currentPage: Int): String? {
        return if (currentPage == 1) null else ((currentPage - 1) * COLLECTION_PAGE_LIMIT).toString()
    }

    private fun clearCollectionsByRequestType() {
        localRepository.clearCollectionsByRequestType(requestType)
    }

    private fun getEntitiesByDataResponse(
        collectionResponse: CollectionResponse,
        page: Int,
    ): List<CollectionEntity> {
        return collectionResponse.collectionData.map {
            it.toCollectionEntity(
                collectionType = collectionType.collectionName,
                typeOfRequest = requestType,
                page = page,
            )
        }
    }

    open fun toLoadResult(
        collection: List<Collection>,
        currentPage: Int,
    ): LoadResult<Int, Collection> {
        return LoadResult.Page(
            data = collection,
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = if (collection.isEmpty()) null else currentPage + 1,
        )
    }
}
