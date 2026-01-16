package com.spoonofcode.poa.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoonofcode.poa.core.network.NetworkManager
import com.spoonofcode.poa.core.ui.navigation.ViewModelNavigator
import com.spoonofcode.poa.core.ui.snackbar.SnackbarEvent
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin

abstract class BaseViewModel<VS : BaseViewState, VA : BaseViewAction>(
    initialViewState: VS,
    initialScreenState: ScreenState = ScreenState.LOADING // Start Screen with LoadingView
) : ViewModel() {

    protected val viewModelNavigator: ViewModelNavigator by getKoin().inject()
    protected val networkManager: NetworkManager by getKoin().inject()

    val navigationFlow = viewModelNavigator.navigationEvents

    private val _viewState = MutableStateFlow(ViewState(initialViewState, initialScreenState))
    val viewState = _viewState.asStateFlow()

    private val _isOnline = MutableStateFlow(false)

    private val _snackbarEvent = MutableSharedFlow<SnackbarEvent>()
    val snackbarEvent = _snackbarEvent.asSharedFlow()

    init {
        observeNetworkState()
    }

    open fun onAction(action: VA) {}

    open fun navigateBack() {
        viewModelScope.launch {
            viewModelNavigator.pop()
        }
    }

    fun currentState(): VS = viewState.value.data

    fun updateState(
        transformation: VS.() -> VS,
    ) {
        _viewState.update { it.copy(data = it.data.transformation()) }
    }

    fun showSnackbar(snackbarEvent: SnackbarEvent) {
        viewModelScope.launch {
            _snackbarEvent.emit(snackbarEvent)
        }
    }

    protected fun showLoadingView() {
        _viewState.update { it.copy(screenState = ScreenState.LOADING) }
    }

    protected fun showErrorView() {
        _viewState.update { it.copy(screenState = ScreenState.ERROR) }
    }

    protected fun showContentView(
        transformation: VS.() -> VS,
    ) {
        _viewState.update {
            it.copy(
                screenState = ScreenState.CONTENT,
                data = it.data.transformation(),
            )
        }
    }

    protected fun showErrorSnackbar(e: Throwable) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        _viewState.update {
            it.copy(screenState = ScreenState.CONTENT)
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeNetworkState() {
        viewModelScope.launch {
            var hasInitialized = false
            networkManager.observeNetworkState()
                .distinctUntilChangedBy { it.isConnected } // only online/offline changes
                .debounce(500) // debounce to avoid rapid changes
                .collect { status ->
                    when (status) {
                        is Connectivity.Status.Connected -> {
                            if (hasInitialized) { // Avoid showing snackbar on initial value
                                showSnackbar(SnackbarEvent.Online)
                            }
                            _isOnline.value = true
                            hasInitialized = true
                        }

                        is Connectivity.Status.Disconnected -> {
                            _isOnline.value = false
                            showSnackbar(SnackbarEvent.Offline)
                            hasInitialized = true
                        }
                    }
                }
        }
    }
}