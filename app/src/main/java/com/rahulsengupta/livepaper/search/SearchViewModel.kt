package com.rahulsengupta.livepaper.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.rahulsengupta.core.usecase.LoadPhotosUseCase
import com.rahulsengupta.livepaper.home.model.PhotoItem
import kotlinx.coroutines.flow.map

class SearchViewModel @ViewModelInject constructor(
    loadPhotosUseCase: LoadPhotosUseCase
) : ViewModel() {

    val popularPhotoFlow = loadPhotosUseCase.popularPhotoFlow.map { pagingData ->
        pagingData.map {
            PhotoItem(
                id = it.id,
                imageUrl = it.urls?.regular ?: ""
            )
        }
    }.cachedIn(viewModelScope)

}