package com.rahulsengupta.livepaper.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.core.coroutine.CoroutinesDispatcher
import com.rahulsengupta.core.usecase.LoadTrendingCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    loadTrendingCollectionsUseCase: LoadTrendingCollectionsUseCase,
    coroutineDispatcher: CoroutinesDispatcher
) : ViewModel() {

    private val _command = MutableLiveData<Command>()
    val command: LiveData<Command>
        get() = _command

    init {
        viewModelScope.launch(coroutineDispatcher.io) {
            loadTrendingCollectionsUseCase.invoke(1, 10)
        }
        _command.value = Command.NavigateToIndex(0)
    }

    fun onHomeClicked() {
        _command.value = Command.NavigateToIndex(0)
    }

    fun onSearchClicked() {
        _command.value = Command.NavigateToIndex(1)
    }

    fun onFavoritesClicked() {
        _command.value = Command.NavigateToIndex(2)
    }

    fun onSettingsClicked() {
        _command.value = Command.NavigateToIndex(3)
    }
}

sealed class Command {
    data class NavigateToIndex(val index: Int) : Command()
}