package com.rahulsengupta.core.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rahulsengupta.core.paging.home.PhotosPagingSource
import com.rahulsengupta.core.paging.home.PhotosRemoteMediator
import com.rahulsengupta.datasource.UnSplashDataSource
import com.rahulsengupta.persistence.LivePaperDatabase
import com.rahulsengupta.persistence.entity.LatestPhotoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class LoadPhotosUseCase @Inject constructor(
    private val database: LivePaperDatabase,
    private val dataSource: UnSplashDataSource
) {

    val popularPhotoFlow = Pager(PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE)) {
        PhotosPagingSource(dataSource, ORDER_BY_POPULAR)
    }.flow

    fun getLatestPhotosStream(): Flow<PagingData<LatestPhotoEntity>> {
        val pagingSourceFactory = { database.latestPhotosDao().photos() }
        val remoteMediator = PhotosRemoteMediator(database, dataSource)

        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE, enablePlaceholders = true),
            remoteMediator = remoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 12
        const val ORDER_BY_LATEST = "latest"
        const val ORDER_BY_POPULAR = "popular"
    }
}