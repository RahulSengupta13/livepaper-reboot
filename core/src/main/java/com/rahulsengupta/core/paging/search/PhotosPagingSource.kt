package com.rahulsengupta.core.paging.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
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
                pageSize = params.loadSize,
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

    override fun getRefreshKey(state: PagingState<Int, PhotoResponse>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}