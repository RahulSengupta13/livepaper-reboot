package com.rahulsengupta.core.ui

import android.graphics.Bitmap
import android.graphics.ColorFilter
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.size.ViewSizeResolver
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.rahulsengupta.core.R
import timber.log.Timber

fun ImageView.loadImageWithPalette(url: String, block: (GradientDrawable, Int) -> Unit = { _, _ -> }) {
    val context = context
    val request = ImageRequest.Builder(context)
        .data(url)
        .size(ViewSizeResolver(this))
        .crossfade(true)
        .target(onSuccess = { drawable ->
            setImageDrawable(drawable)
            val bitmap = ImageUtils.drawableToBitmap(drawable).copy(Bitmap.Config.ARGB_8888, true)
            Palette.from(bitmap).generate {
                it ?: return@generate
                Timber.d(it.toString())
                val darkVibrantColor = it.getDarkVibrantColor(ContextCompat.getColor(context, R.color.dark))
                val gradientColors = arrayOf(darkVibrantColor, ContextCompat.getColor(context, android.R.color.transparent)).toIntArray()
                val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, gradientColors)
                block(gradientDrawable, it.darkVibrantSwatch?.titleTextColor ?: ContextCompat.getColor(context, R.color.light))
            }
        })
        .build()
    context.imageLoader.enqueue(request)
}

fun LottieAnimationView.changeLayersColor(color: Int) {
    val filter = SimpleColorFilter(color)
    val keyPath = KeyPath("**")
    val callback: LottieValueCallback<ColorFilter> = LottieValueCallback(filter)

    addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback)
}