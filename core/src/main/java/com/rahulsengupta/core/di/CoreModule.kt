package com.rahulsengupta.core.di

import com.rahulsengupta.core.sharedprefs.LivePaperSharedPrefs
import com.rahulsengupta.core.sharedprefs.LivePaperSharedPrefsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class CoreModule {

    @Singleton
    @Binds
    abstract fun bindLivePaperSharedPrefs(prefs: LivePaperSharedPrefsImpl): LivePaperSharedPrefs
}
