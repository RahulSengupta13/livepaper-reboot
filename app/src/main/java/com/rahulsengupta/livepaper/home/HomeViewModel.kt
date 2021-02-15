package com.rahulsengupta.livepaper.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rahulsengupta.core.usecase.LoadPhotosUseCase
import com.rahulsengupta.livepaper.home.model.PhotoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadPhotosUseCase: LoadPhotosUseCase
) : ViewModel() {

    @ExperimentalPagingApi
    fun fetchLatestPhotos(): Flow<PagingData<PhotoItem>> {
        return loadPhotosUseCase.getLatestPhotosStream()
            .map { pagingData ->
                pagingData.map {
                    PhotoItem(
                        id = it.id,
                        imageUrl = it.urls?.regular ?: ""
                    )
                }
            }
            .cachedIn(viewModelScope)
    }
}