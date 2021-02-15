package com.rahulsengupta.livepaper.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.rahulsengupta.core.usecase.LoadPhotosUseCase
import com.rahulsengupta.core.usecase.LoadTopicsUseCase
import com.rahulsengupta.livepaper.home.model.PhotoItem
import com.rahulsengupta.livepaper.search.model.TopicsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    loadPhotosUseCase: LoadPhotosUseCase,
    loadTopicsUseCase: LoadTopicsUseCase
) : ViewModel() {

    val latestPhotosFlow = loadPhotosUseCase.latestPhotosFlow.map { pagingData ->
        pagingData.map {
            PhotoItem(
                id = it.id,
                imageUrl = it.urls?.regular ?: "",
                authorImageUrl = it.user?.image?.medium ?: "",
                width = it.width,
                height = it.height
            )
        }
    }.cachedIn(viewModelScope)

    val topicsFlow = loadTopicsUseCase.topicsFlow.map { pagingData ->
        pagingData.map {
            TopicsItem(
                id = it.id,
                title = it.title,
                coverPhotoUrl = it.coverPhoto.urls?.regular ?: ""
            )
        }
    }.cachedIn(viewModelScope)

}