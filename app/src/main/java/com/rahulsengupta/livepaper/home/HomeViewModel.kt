package com.rahulsengupta.livepaper.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rahulsengupta.core.usecase.LoadPhotosUseCase
import com.rahulsengupta.livepaper.home.ViewEffect.RefreshLatestPhotos
import com.rahulsengupta.livepaper.home.ViewEffect.ScrollToTop
import com.rahulsengupta.livepaper.home.model.PhotoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadPhotosUseCase: LoadPhotosUseCase
) : ViewModel() {

    private val _viewEffect = MutableLiveData<ViewEffect>()
    val viewEffect: LiveData<ViewEffect> = _viewEffect

    @ExperimentalPagingApi
    fun fetchPopularPhotos(): Flow<PagingData<PhotoItem>> {
        return loadPhotosUseCase.getPopularPhotosFlow()
            .map { pagingData ->
                pagingData.map {
                    PhotoItem(
                        id = it.id,
                        imageUrl = it.urls?.regular ?: "",
                        authorImageUrl = it.user?.image?.medium ?: "",
                        width = it.width,
                        height = it.height
                    )
                }
            }
            .cachedIn(viewModelScope)
    }

    fun onFabClicked() {
        _viewEffect.value = ScrollToTop
    }

    fun onRefreshRequested() {
        _viewEffect.value = RefreshLatestPhotos
    }
}

sealed class ViewEffect {
    object ScrollToTop : ViewEffect()

    object RefreshLatestPhotos : ViewEffect()
}