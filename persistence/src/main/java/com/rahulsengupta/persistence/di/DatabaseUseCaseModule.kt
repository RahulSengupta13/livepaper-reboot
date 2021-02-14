package com.rahulsengupta.persistence.di

import com.rahulsengupta.persistence.usecase.GetFeaturedCollectionUseCase
import com.rahulsengupta.persistence.usecase.GetFeaturedCollectionUseCaseImpl
import com.rahulsengupta.persistence.usecase.GetTrendingCollectionUseCase
import com.rahulsengupta.persistence.usecase.GetTrendingCollectionUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseUseCaseModule {

    @Binds
    abstract fun bindGetFeaturedCollectionUseCase(useCase: GetFeaturedCollectionUseCaseImpl): GetFeaturedCollectionUseCase

    @Binds
    abstract fun bindGetTrendingCollectionUseCase(useCase: GetTrendingCollectionUseCaseImpl): GetTrendingCollectionUseCase
}