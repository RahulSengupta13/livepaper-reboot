package com.rahulsengupta.core.coroutine

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object CoroutineModule {

    @Provides
    fun bindCoroutineDispatcher(): CoroutinesDispatcher = CoroutinesDispatcherImpl
}