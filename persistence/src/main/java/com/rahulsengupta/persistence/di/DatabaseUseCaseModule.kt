package com.rahulsengupta.persistence.di

import com.rahulsengupta.persistence.usecase.GetFeaturedCollectionUseCase
import com.rahulsengupta.persistence.usecase.GetFeaturedCollectionUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class DatabaseUseCaseModule {

    @Binds
    abstract fun bindGetFeaturedCollectionUseCase(useCase: GetFeaturedCollectionUseCaseImpl): GetFeaturedCollectionUseCase
}