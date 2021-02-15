package com.rahulsengupta.livepaper.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
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

@BindingAdapter("refreshListener")
fun setRefreshListener(view: SwipeRefreshLayout, listener: SwipeRefreshLayout.OnRefreshListener) {
    view.setOnRefreshListener(listener)
}