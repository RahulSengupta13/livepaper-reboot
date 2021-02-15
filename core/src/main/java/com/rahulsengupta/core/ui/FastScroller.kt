package com.rahulsengupta.core.ui

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller

class FastScroller(context: Context): LinearSmoothScroller(context) {
    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
        return MS_PER_INCH / displayMetrics.densityDpi
    }

    companion object {
        private const val MS_PER_INCH = 50f
    }
}