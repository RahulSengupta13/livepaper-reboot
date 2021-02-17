package com.rahulsengupta.core.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rahulsengupta.core.paging.home.collections.CollectionsRemoteMediator
import com.rahulsengupta.datasource.UnSplashDataSource
import com.rahulsengupta.persistence.LivePaperDatabase
import com.rahulsengupta.persistence.entity.CollectionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class LoadCollectionsUseCase @Inject constructor(
    private val dataSource: UnSplashDataSource,
    private val database: LivePaperDatabase
)  {

    fun getCollectionsFlow(): Flow<PagingData<CollectionEntity>> {
        val pagingSourceFactory = { database.collectionDao().collections() }
        val remoteMediator = CollectionsRemoteMediator(database, dataSource)

        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE, enablePlaceholders = false),
            remoteMediator = remoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val PAGE_SIZE = 30
        const val PREFETCH_DISTANCE = 12
    }
}