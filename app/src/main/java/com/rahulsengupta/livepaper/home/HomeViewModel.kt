package com.rahulsengupta.livepaper.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.core.coroutine.CoroutinesDispatcher
import com.rahulsengupta.core.usecase.LoadFeatureCollectionsUseCase
import com.rahulsengupta.persistence.usecase.GetFeaturedCollectionUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel @ViewModelInject constructor(
    private val loadFeatureCollections: LoadFeatureCollectionsUseCase,
    private val getFeaturedCollections: GetFeaturedCollectionUseCase,
    private val dispatcher: CoroutinesDispatcher
) : ViewModel() {

    init {
        viewModelScope.launch(dispatcher.io) {
            getFeaturedCollections().collect {
                Timber.d(it.toString())
            }
        }
    }

    fun initialize() {
        viewModelScope.launch(dispatcher.io) {
            loadFeatureCollections.invoke(1, 10)
        }
    }
}