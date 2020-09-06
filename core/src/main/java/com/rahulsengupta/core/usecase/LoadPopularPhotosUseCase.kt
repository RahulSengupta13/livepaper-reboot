package com.rahulsengupta.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rahulsengupta.core.coroutine.CoroutinesDispatcher
import com.rahulsengupta.core.paging.PopularPhotosPagingSource
import com.rahulsengupta.datasource.UnSplashDataSource
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoadPopularPhotosUseCase @Inject constructor(
    private val dataSource: UnSplashDataSource,
    private val dispatcher: CoroutinesDispatcher
) {

    fun getPopularPhotosStream() = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE)
    ) {
        PopularPhotosPagingSource(dataSource, ORDER_BY)
    }.flow.flowOn(dispatcher.io)

    companion object {
        const val PAGE_SIZE = 10
        const val ORDER_BY = "popular"
    }
}