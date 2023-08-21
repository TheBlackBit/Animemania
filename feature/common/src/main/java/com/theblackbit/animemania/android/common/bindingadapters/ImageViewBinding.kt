package com.theblackbit.animemania.android.common.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("loadImage")
fun ImageView.setImageSrc(url: String?) {
    Glide.with(context)
        .load(url)
        .apply(
            RequestOptions().dontTransform()
        )
        .into(this)
}
