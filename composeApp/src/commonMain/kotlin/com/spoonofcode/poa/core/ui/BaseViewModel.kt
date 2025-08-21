package com.spoonofcode.poa.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.network.NetworkManager
import com.spoonofcode.poa.core.ui.navigation.ViewModelNavigator
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin

abstract class BaseViewModel<VS : BaseViewState>(
    initialViewState: VS
) : ViewModel() {

    protected val viewModelNavigator: ViewModelNavigator by getKoin().inject()
    protected val networkManager: NetworkManager by getKoin().inject()

    val navigationFlow = viewModelNavigator.navigationEvents

    private val _viewState = MutableStateFlow(initialViewState)
    val viewState = _viewState.asStateFlow()

    private val _isOnline = MutableStateFlow(false)
    private val isOnline: StateFlow<Boolean> = _isOnline

    private val _snackbarEvent = MutableSharedFlow<SnackbarEvent>()
    val snackbarEvent = _snackbarEvent.asSharedFlow()

    init {
        observeNetworkState()
    }

    open fun navigateBack() {
        viewModelScope.launch {
            viewModelNavigator.pop()
        }
    }

    fun currentState(): VS = viewState.value

    fun updateState(
        transformation: VS.() -> VS,
    ) {
        val currentState = currentState()
        val newState = currentState.transformation()
        _viewState.value = newState
    }

    fun showSnackbar(snackbarEvent: SnackbarEvent) {
        viewModelScope.launch {
            _snackbarEvent.emit(snackbarEvent)
        }
    }

    private fun observeNetworkState() {
        viewModelScope.launch {
            networkManager.observeNetworkState().distinctUntilChanged().collect { status ->
                when (status) {
                    is Connectivity.Status.Connected -> {
                        _isOnline.value = true
                        showSnackbar(SnackbarEvent.Online)
                    }
                    is Connectivity.Status.Disconnected -> {
                        _isOnline.value = false
                        showSnackbar(SnackbarEvent.Offline)
                    }
                }
            }
        }
    }
}