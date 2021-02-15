package com.rahulsengupta.livepaper

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import timber.log.Timber

@HiltAndroidApp
open class LivePaperApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeCoil()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this);
        }
    }

    /*
    * TODO: Get this into Hilt
    * */
    private fun initializeCoil() {
        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(this))
                    .build()
            }
            .build()
        Coil.setImageLoader(imageLoader)
    }
}