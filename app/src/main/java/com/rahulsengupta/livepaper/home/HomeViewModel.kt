package com.rahulsengupta.livepaper.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.rahulsengupta.core.coroutine.CoroutinesDispatcher
import com.rahulsengupta.core.usecase.LoadFeatureCollectionsUseCase
import com.rahulsengupta.core.usecase.LoadPhotosUseCase
import com.rahulsengupta.livepaper.home.model.FeaturedCollectionItem
import com.rahulsengupta.livepaper.home.model.PhotoItem
import com.rahulsengupta.persistence.entity.FeaturedCollectionEntity
import com.rahulsengupta.persistence.usecase.GetFeaturedCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcher: CoroutinesDispatcher,
    private val loadFeatureCollections: LoadFeatureCollectionsUseCase,
    private val getFeaturedCollections: GetFeaturedCollectionUseCase,
    private val loadPhotosUseCase: LoadPhotosUseCase
) : ViewModel() {

    private val _featuredCollections = MutableLiveData<List<FeaturedCollectionItem>>()
    val featuredCollections: LiveData<List<FeaturedCollectionItem>>
        get() = _featuredCollections

    init {
        initialize()
        viewModelScope.launch(dispatcher.io) {
            getFeaturedCollections().collect {
                processFeaturedCollection(it)
            }
        }
    }

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

    private fun initialize() {
        viewModelScope.launch(dispatcher.io) {
            loadFeatureCollections.invoke(1, 10)
        }
    }

    private fun processFeaturedCollection(list: List<FeaturedCollectionEntity>?) {
        val collections = list?.map {
            FeaturedCollectionItem(
                title = it.title ?: "",
                imageUrl = it.coverPhoto.urls?.regular ?: ""
            )
        } ?: return
        _featuredCollections.postValue(collections)
    }
}