package com.rahulsengupta.livepaper.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.rahulsengupta.core.coroutine.CoroutinesDispatcher
import com.rahulsengupta.core.usecase.LoadFeatureCollectionsUseCase
import com.rahulsengupta.core.usecase.LoadPopularPhotosUseCase
import com.rahulsengupta.livepaper.home.model.FeaturedCollectionItem
import com.rahulsengupta.livepaper.home.model.PopularPhotoItem
import com.rahulsengupta.persistence.entity.FeaturedCollectionEntity
import com.rahulsengupta.persistence.usecase.GetFeaturedCollectionUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val loadFeatureCollections: LoadFeatureCollectionsUseCase,
    private val getFeaturedCollections: GetFeaturedCollectionUseCase,
    private val dispatcher: CoroutinesDispatcher,
    loadPopularPhotosUseCase: LoadPopularPhotosUseCase
) : ViewModel() {

    private val _featuredCollections = MutableLiveData<List<FeaturedCollectionItem>>()
    val featuredCollections: LiveData<List<FeaturedCollectionItem>>
        get() = _featuredCollections

    val popularPhotoFlow = loadPopularPhotosUseCase.popularPhotoFlow
        .map { pagingData ->
            pagingData.map {
                PopularPhotoItem(
                    id = it.id,
                    imageUrl = it.urls?.regular ?: ""
                )
            }
        }
        .cachedIn(viewModelScope)

    init {
        initialize()
        viewModelScope.launch(dispatcher.io) {
            getFeaturedCollections().collect {
                processFeaturedCollection(it)
            }
        }
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