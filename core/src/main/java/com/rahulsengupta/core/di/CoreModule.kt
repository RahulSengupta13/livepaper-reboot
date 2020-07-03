package com.rahulsengupta.core.di

import com.rahulsengupta.core.usecase.LoadFeatureCollectionsUseCase
import com.rahulsengupta.core.usecase.LoadFeatureCollectionsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class CoreModule {

    @Binds
    abstract fun bindLoadFeatureCollectionsUseCase(useCase: LoadFeatureCollectionsUseCaseImpl): LoadFeatureCollectionsUseCase
}
