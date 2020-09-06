package com.rahulsengupta.livepaper.bindingadapters

import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.mikhaellopez.circularimageview.CircularImageView
import com.rahulsengupta.livepaper.R
import com.rahulsengupta.livepaper.activity.ViewState

@BindingAdapter("app:bottomBarDestination")
fun bottomBarDestination(motionLayout: MotionLayout, viewState: ViewState?) {
    val state = viewState ?: return
    motionLayout.run {
        val endState = when (state) {
            is ViewState.NavigateToHome -> R.id.home_expand
            is ViewState.NavigateToSearch -> R.id.search_expand
            is ViewState.NavigateToFavorites -> R.id.like_expand
            is ViewState.NavigateToSettings -> R.id.profile_expand
        }
        setTransition(currentState, endState)
        setTransitionDuration(250)
        transitionToEnd()
    }
}


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