package com.theblackbit.animemania.android.common.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun ImageView.setImageSrc(url: String?) {
    Glide.with(context).load(url).into(this)
}
