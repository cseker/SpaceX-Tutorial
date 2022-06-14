package com.cihadseker.core.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String?) {
    view.load(url)
}