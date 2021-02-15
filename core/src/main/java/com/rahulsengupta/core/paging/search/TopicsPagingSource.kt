package com.rahulsengupta.core.paging.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rahulsengupta.datasource.UnSplashDataSource
import com.rahulsengupta.model.response.TopicsResponse

class TopicsPagingSource(
    private val dataSource: UnSplashDataSource,
    private val orderBy: String
) : PagingSource<Int, TopicsResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopicsResponse> {
        return try {
            val pageToLoad = params.key ?: 1

            val response = dataSource.getTopics(
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

    override fun getRefreshKey(state: PagingState<Int, TopicsResponse>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}