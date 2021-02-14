package com.rahulsengupta.livepaper.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.mikhaellopez.circularimageview.CircularImageView

@BindingAdapter("loadImage")
fun loadImage(circularImageView: CircularImageView, imageUrl: String?) {
    imageUrl?.let {
        circularImageView.load(it)
    }
}

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        imageView.load(it) {
            crossfade(true)
        }
    }
}