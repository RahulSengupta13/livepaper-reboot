package com.rahulsengupta.core.paging

import androidx.paging.PagingSource
import com.rahulsengupta.core.usecase.LoadPhotosUseCase
import com.rahulsengupta.datasource.UnSplashDataSource
import com.rahulsengupta.model.response.PhotoResponse

class PhotosPagingSource(
    private val dataSource: UnSplashDataSource,
    private val orderBy: String
) : PagingSource<Int, PhotoResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponse> {
        return try {
            val pageToLoad = params.key ?: 1

            val response = dataSource.getPhotos(
                page = pageToLoad,
                pageSize = LoadPhotosUseCase.PAGE_SIZE,
                orderBy = orderBy
            )
            val data = response.data ?: return LoadResult.Error(Exception())

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = pageToLoad + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}