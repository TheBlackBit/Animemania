package com.theblackbit.animemania.android.common.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2

@BindingAdapter("enableSwipe")
fun ViewPager2.disableScroll(enable: Boolean) {
    isUserInputEnabled = enable
}
