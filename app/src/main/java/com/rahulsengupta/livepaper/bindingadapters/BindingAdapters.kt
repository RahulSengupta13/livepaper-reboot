package com.rahulsengupta.livepaper.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.mikhaellopez.circularimageview.CircularImageView

@BindingAdapter("app:loadImage")
fun loadImage(circularImageView: CircularImageView, imageUrl: String?) {
    imageUrl?.let {
        circularImageView.load(it)
    }
}

@BindingAdapter("app:loadImage")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        imageView.load(it) {
            crossfade(true)
        }
    }
}