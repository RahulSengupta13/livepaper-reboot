package com.rahulsengupta.persistence.di

import com.rahulsengupta.persistence.LivePaperDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {

    @Provides
    fun provideFeaturedCollectionDao(database: LivePaperDatabase) = database.collectionDao()

    @Provides
    fun provideLatestPhotosDao(database: LivePaperDatabase) = database.latestPhotosDao()

    @Provides
    fun provideLatestPhotoRemoteKeyDao(database: LivePaperDatabase) = database.latestPhotoRemoteKeyDao()

    @Provides
    fun provideCollectionsRemoteKeyDao(database: LivePaperDatabase) = database.collectionsRemoteKeyDao()
}