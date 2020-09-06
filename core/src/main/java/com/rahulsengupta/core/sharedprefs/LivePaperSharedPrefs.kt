package com.rahulsengupta.core.sharedprefs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

interface LivePaperSharedPrefs {

}

@Singleton
class LivePaperSharedPrefsImpl @Inject constructor(
    @ApplicationContext val context: Context
) : LivePaperSharedPrefs {

    private val sharedPrefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS = "live_paper_prefs"
    }
}