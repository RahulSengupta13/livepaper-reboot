package com.rahulsengupta.core.ui

import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.size.ViewSizeResolver
import com.rahulsengupta.core.R
import timber.log.Timber

fun ImageView.loadImageWithPalette(url: String, block: (GradientDrawable) -> Unit = {}) {
    val context = context
    val request = ImageRequest.Builder(context)
        .data(url)
        .size(ViewSizeResolver(this))
        .target(onSuccess = { drawable ->
            load(drawable) {
                crossfade(true)
            }

            val bitmap = ImageUtils.drawableToBitmap(drawable).copy(Bitmap.Config.ARGB_8888, true)
            Palette.from(bitmap).generate {
                it ?: return@generate
                Timber.d(it.toString())
                val darkVibrantColor = it.getDarkMutedColor(ContextCompat.getColor(context, R.color.dark))
                val gradientColors = arrayOf(darkVibrantColor, ContextCompat.getColor(context, android.R.color.transparent)).toIntArray()
                val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, gradientColors)
                block(gradientDrawable)
            }
        })
        .build()
    context.imageLoader.enqueue(request)
}