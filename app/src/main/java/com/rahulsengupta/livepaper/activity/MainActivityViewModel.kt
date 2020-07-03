package com.rahulsengupta.livepaper.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel @ViewModelInject constructor() : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    init {
        _viewState.value = ViewState.NavigateToHome
    }

    fun onHomeClicked() {
        _viewState.value = ViewState.NavigateToHome
    }

    fun onSearchClicked() {
        _viewState.value = ViewState.NavigateToSearch
    }

    fun onFavoritesClicked() {
        _viewState.value = ViewState.NavigateToFavorites
    }

    fun onSettingsClicked() {
        _viewState.value = ViewState.NavigateToSettings
    }
}

sealed class ViewState {
    object NavigateToHome : ViewState()
    object NavigateToSearch : ViewState()
    object NavigateToFavorites : ViewState()
    object NavigateToSettings : ViewState()
}