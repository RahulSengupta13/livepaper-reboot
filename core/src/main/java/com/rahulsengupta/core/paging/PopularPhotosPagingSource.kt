package com.rahulsengupta.core.paging

import androidx.paging.PagingSource
import com.rahulsengupta.core.usecase.LoadPopularPhotosUseCase
import com.rahulsengupta.datasource.UnSplashDataSource
import com.rahulsengupta.model.response.PhotoResponse

class PopularPhotosPagingSource(
    private val dataSource: UnSplashDataSource,
    private val orderBy: String
) : PagingSource<Int, PhotoResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponse> {
        val nextPageNumber = params.key ?: 1

        val response = dataSource.getPhotos(
            page = nextPageNumber,
            pageSize = LoadPopularPhotosUseCase.PAGE_SIZE,
            orderBy = orderBy
        )

        return LoadResult.Page(
            data = response.data ?: emptyList(),
            prevKey = null,
            nextKey = nextPageNumber + 1
        )
    }
}