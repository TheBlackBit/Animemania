package com.theblackbit.animemania.android.data.pagingsource.collection.manga

import com.theblackbit.animemania.android.data.external.datasource.response.collectionresponse.CollectionResponse
import com.theblackbit.animemania.android.data.internal.repository.CollectionLocalRepository
import com.theblackbit.animemania.android.data.pagingsource.collection.CollectionPagingSource
import com.theblackbit.animemania.android.util.SafeApiRequest
import io.reactivex.rxjava3.core.Single

class PopularMangaPagingSource(
    localRepository: CollectionLocalRepository,
    request: (pageLimit: String, pageOffset: String?) -> Single<SafeApiRequest.ApiResultHandle<CollectionResponse>>,
    categoryId: Int,
) : CollectionPagingSource(
    localRepository,
    request,
    categoryId,
)
