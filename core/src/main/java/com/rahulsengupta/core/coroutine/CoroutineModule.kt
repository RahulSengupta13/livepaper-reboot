package com.rahulsengupta.core.coroutine

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object CoroutineModule {

    @Provides
    fun bindCoroutineDispatcher(): CoroutinesDispatcher = CoroutinesDispatcherImpl
}