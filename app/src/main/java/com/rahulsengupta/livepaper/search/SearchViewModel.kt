package com.rahulsengupta.livepaper.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.rahulsengupta.core.coroutine.CoroutinesDispatcher
import com.rahulsengupta.core.usecase.LoadPhotosUseCase
import com.rahulsengupta.livepaper.home.model.PhotoItem
import com.rahulsengupta.livepaper.search.model.TrendingCollectionItem
import com.rahulsengupta.persistence.usecase.GetTrendingCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    loadPhotosUseCase: LoadPhotosUseCase,
    coroutinesDispatcher: CoroutinesDispatcher,
    getTrendingCollectionUseCase: GetTrendingCollectionUseCase
) : ViewModel() {

    private val _trendingCollections = MutableLiveData<List<TrendingCollectionItem>>()
    val trendingCollections: LiveData<List<TrendingCollectionItem>>
        get() = _trendingCollections

    init {
        viewModelScope.launch(coroutinesDispatcher.io) {
            getTrendingCollectionUseCase().collect { list ->
                val mappedList = list?.map { TrendingCollectionItem.Regular(it.title ?: "", imageUrl = it.coverPhoto.urls?.regular ?: "") } ?: emptyList()
                val newList = mutableListOf<TrendingCollectionItem>().also {
                    it.add(TrendingCollectionItem.Empty)
                    it.addAll(mappedList)
                }
                _trendingCollections.postValue(newList)
            }
        }
    }

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

}