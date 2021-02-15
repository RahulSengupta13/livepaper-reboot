package com.rahulsengupta.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rahulsengupta.core.paging.search.TopicsPagingSource
import com.rahulsengupta.datasource.UnSplashDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadTopicsUseCase @Inject constructor(
    private val dataSource: UnSplashDataSource
)  {

    val topicsFlow = Pager(PagingConfig(pageSize = LoadPhotosUseCase.PAGE_SIZE, prefetchDistance = LoadPhotosUseCase.PREFETCH_DISTANCE)) {
        TopicsPagingSource(dataSource, ORDER_BY_POPULAR)
    }.flow

    companion object {
        const val PAGE_SIZE = 30
        const val PREFETCH_DISTANCE = 12
        const val ORDER_BY_LATEST = "latest"
        const val ORDER_BY_POPULAR = "popular"
    }
}