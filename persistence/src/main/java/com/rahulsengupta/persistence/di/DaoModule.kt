package com.rahulsengupta.persistence.di

import com.rahulsengupta.persistence.LivePaperDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object DaoModule {

    @Provides
    fun provideFeaturedCollectionDao(database: LivePaperDatabase) = database.collectionEntityDao()

    @Provides
    fun provideTrendingCollectionDao(database: LivePaperDatabase) = database.trendingCollectionEntityDao()
}