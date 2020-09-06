package com.rahulsengupta.livepaper.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel @ViewModelInject constructor() : ViewModel() {

    private val _viewEffect = MutableLiveData<ViewEffect>()
    val viewEffect: LiveData<ViewEffect>
        get() = _viewEffect

    init {
        _viewEffect.value = ViewEffect.NavigateToHome
    }

    fun onHomeClicked() {
        _viewEffect.value = ViewEffect.NavigateToHome
    }

    fun onSearchClicked() {
        _viewEffect.value = ViewEffect.NavigateToSearch
    }

    fun onFavoritesClicked() {
        _viewEffect.value = ViewEffect.NavigateToFavorites
    }

    fun onSettingsClicked() {
        _viewEffect.value = ViewEffect.NavigateToSettings
    }
}

sealed class ViewEffect {
    object NavigateToHome : ViewEffect()
    object NavigateToSearch : ViewEffect()
    object NavigateToFavorites : ViewEffect()
    object NavigateToSettings : ViewEffect()
}