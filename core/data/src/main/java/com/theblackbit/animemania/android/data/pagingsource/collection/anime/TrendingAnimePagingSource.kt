package com.theblackbit.animemania.android.data.pagingsource.collection.anime

import com.theblackbit.animemania.android.data.external.datasource.RequestType
import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.collection.CollectionPagingSource
import com.theblackbit.animemania.android.model.Collection
import com.theblackbit.animemania.android.model.CollectionType
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single

class TrendingAnimePagingSource(
    localRepository: CollectionLocalRepository,
    request: (pageLimit: String, pageOffset: String?) -> Single<SafeApiRequest.ApiResultHandle<CollectionResponse>>,
    requestType: RequestType
) : CollectionPagingSource(
    localRepository,
    request,
    requestType,
    CollectionType.ANIME
) {

    override fun toLoadResult(
        collection: List<Collection>,
        currentPage: Int
    ): LoadResult<Int, Collection> {
        return LoadResult.Page(
            data = collection,
            prevKey = null,
            nextKey = null
        )
    }
}
