package com.rahulsengupta.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rahulsengupta.core.paging.PhotosPagingSource
import com.rahulsengupta.datasource.UnSplashDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadPhotosUseCase @Inject constructor(
    private val dataSource: UnSplashDataSource
) {

    val latestPhotoFlow = Pager(PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE)) {
        PhotosPagingSource(dataSource, ORDER_BY_LATEST)
    }.flow

    val popularPhotoFlow = Pager(PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PREFETCH_DISTANCE)) {
        PhotosPagingSource(dataSource, ORDER_BY_POPULAR)
    }.flow

    companion object {
        const val PAGE_SIZE = 25
        const val PREFETCH_DISTANCE = 13
        const val ORDER_BY_LATEST = "latest"
        const val ORDER_BY_POPULAR = "popular"
    }
}