package com.rahulsengupta.livepaper.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulsengupta.core.coroutine.CoroutinesDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(
    coroutinesDispatcher: CoroutinesDispatcher
) : ViewModel() {

    private val _command = MutableLiveData<Command>()
    val command: LiveData<Command>
        get() = _command

    init {
        viewModelScope.launch(coroutinesDispatcher.io) {
            delay(1000)
            _command.postValue(Command.NavigateToMainFragment)
        }
    }
}

sealed class Command {
    object NavigateToMainFragment : Command()
}