package com.rahulsengupta.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rahulsengupta.core.paging.PopularPhotosPagingSource
import com.rahulsengupta.datasource.UnSplashDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadPopularPhotosUseCase @Inject constructor(
    private val dataSource: UnSplashDataSource
) {

    val popularPhotoFlow = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        PopularPhotosPagingSource(dataSource, ORDER_BY)
    }.flow

    companion object {
        const val PAGE_SIZE = 10
        const val ORDER_BY = "popular"
    }
}