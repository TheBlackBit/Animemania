package com.theblackbit.animemania.android.util

import com.theblackbit.animemania.android.util.AppConstants.AVERAGE_RATING_SORT
import com.theblackbit.animemania.android.util.AppConstants.CURRENT_STATUS
import com.theblackbit.animemania.android.util.AppConstants.UPCOMING_STATUS
import com.theblackbit.animemania.android.util.AppConstants.USER_COUNT_SORT

enum class CategoryHolder(val status: String? = null, val sort: String? = null) {
    WEEK_POPULAR(status = CURRENT_STATUS, sort = USER_COUNT_SORT),
    MOST_WANTED(status = UPCOMING_STATUS, sort = USER_COUNT_SORT),
    POPULAR(sort = AVERAGE_RATING_SORT),
    TOP_RATED(sort = USER_COUNT_SORT),
}
