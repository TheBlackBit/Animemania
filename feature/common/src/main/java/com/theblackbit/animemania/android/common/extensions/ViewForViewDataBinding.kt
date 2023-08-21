package com.theblackbit.animemania.android.common.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.viewForViewDataBinding(@LayoutRes layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}
