package com.rahulsengupta.core.di

import com.rahulsengupta.core.sharedprefs.LivePaperSharedPrefs
import com.rahulsengupta.core.sharedprefs.LivePaperSharedPrefsImpl
import com.rahulsengupta.core.usecase.LoadFeatureCollectionsUseCase
import com.rahulsengupta.core.usecase.LoadFeatureCollectionsUseCaseImpl
import com.rahulsengupta.core.usecase.LoadTrendingCollectionsUseCase
import com.rahulsengupta.core.usecase.LoadTrendingCollectionsUseCaseImpl
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
    abstract fun bindLoadFeatureCollectionsUseCase(useCase: LoadFeatureCollectionsUseCaseImpl): LoadFeatureCollectionsUseCase

    @Singleton
    @Binds
    abstract fun bindLoadTrendingCollectionsUseCase(useCase: LoadTrendingCollectionsUseCaseImpl): LoadTrendingCollectionsUseCase

    @Singleton
    @Binds
    abstract fun bindLivePaperSharedPrefs(prefs: LivePaperSharedPrefsImpl): LivePaperSharedPrefs
}
