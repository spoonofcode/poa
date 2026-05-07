package com.spoonofcode.core.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.presentation.compose.snackbar.SnackbarEvent
import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolver
import com.spoonofcode.core.presentation.network.NetworkManager
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
    initialState: ViewState<VS> = ViewState.Loading
) : ViewModel() {

    protected val viewModelNavigator: ViewModelNavigator by getKoin().inject()
    protected val networkManager: NetworkManager by getKoin().inject()
    protected val routeResolver: VoyagerRouteResolver by getKoin().inject()

    val navigationFlow = viewModelNavigator.navigationEvents

    // Bufor na ostatnie znane dane
    private var lastContentData: VS? = if (initialState is ViewState.Content) {
        initialState.data
    } else {
        null
    }

    private val _viewState = MutableStateFlow(initialState)
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

    fun currentContentViewState(): VS {
        return requireNotNull(lastContentData)
    }

    fun updateState(
        transformation: VS.() -> VS,
    ) {
        _viewState.update { (it as ViewState.Content).copy(data = it.data.transformation()) }
        lastContentData = (_viewState.value as ViewState.Content).data
    }

    fun showSnackbar(snackbarEvent: SnackbarEvent) {
        viewModelScope.launch {
            _snackbarEvent.emit(snackbarEvent)
        }
    }

    protected fun showLoadingView() {
        _viewState.value = ViewState.Loading
    }

    protected fun showErrorView() {
        _viewState.value = ViewState.Error
    }

    protected fun showContentView(
        data: VS
    ) {
        _viewState.value = ViewState.Content(data = data)
    }

    protected fun showErrorSnackbar(e: Throwable) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
//        showSnackbar(SnackbarEvent.Error(message = "Something went wrong. Please try again later."))
        // Po błędzie w snackbarze zazwyczaj chcemy wrócić do Content,
        // ale jeśli nie byliśmy w nim wcześniej, musimy zdecydować co pokazać.
//        _viewState.update { currentState ->
//            if (currentState is ViewState.Content) currentState else ViewState.Content(
//                lastContentData
//            )
//        }
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