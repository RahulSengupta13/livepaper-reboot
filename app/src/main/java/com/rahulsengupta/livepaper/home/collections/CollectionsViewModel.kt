package com.rahulsengupta.livepaper.home.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rahulsengupta.core.usecase.LoadCollectionsUseCase
import com.rahulsengupta.livepaper.home.collections.ViewEffect.RefreshCollections
import com.rahulsengupta.livepaper.home.collections.ViewEffect.ScrollToTop
import com.rahulsengupta.livepaper.home.collections.model.CollectionItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class CollectionsViewModel @Inject constructor(
    private val loadCollectionsUseCase: LoadCollectionsUseCase
) : ViewModel() {

    private val _viewEffect = MutableLiveData<ViewEffect>()
    val viewEffect: LiveData<ViewEffect> = _viewEffect

    fun getCollections(): Flow<PagingData<CollectionItem>> {
        return loadCollectionsUseCase.getCollectionsFlow().map { pagingData ->
            pagingData.map {
                CollectionItem(
                    id = it.id,
                    coverPhoto = it.coverPhoto.urls?.regular ?: "",
                    coverPhotoWidth = it.coverPhoto.width,
                    coverPhotoHeight = it.coverPhoto.height
                )
            }
        }.cachedIn(viewModelScope)
    }

    fun onRefreshRequested() {
        _viewEffect.value = RefreshCollections
    }

    fun onFabClicked() {
        _viewEffect.value = ScrollToTop
    }
}

sealed class ViewEffect {
    object ScrollToTop : ViewEffect()

    object RefreshCollections : ViewEffect()
}