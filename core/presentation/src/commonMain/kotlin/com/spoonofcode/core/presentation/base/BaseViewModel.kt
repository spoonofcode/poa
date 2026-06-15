package com.spoonofcode.core.presentation.base

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoonofcode.core.data.ext.DEFAULT_STOP_TIMEOUT_MILLIS
import com.spoonofcode.core.data.ext.stateInWhileSubscribed
import com.spoonofcode.core.presentation.compose.snackbar.SnackbarEvent
import com.spoonofcode.core.presentation.helpers.UiText
import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.core.presentation.network.NetworkManager
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin

/**
 * Base ViewModel implementation for Unidirectional Data Flow.
 *
 * @param VS The type representing the View State.
 * @param VA The type representing User Actions.
 * @param VE The type representing One-off View Events.
 */
abstract class BaseViewModel<VS : BaseViewState, VA : BaseViewAction, VE : BaseViewEvent>(
    protected val initialViewState: ViewState<VS> = ViewState.Initial,
    protected val viewStateStopTimeoutMillis: Long = DEFAULT_STOP_TIMEOUT_MILLIS,
    protected val autoReloadData: Boolean = false,
) :
    ViewModel() {

    private var hasLoadedInitialData = false

    protected val viewModelNavigator: ViewModelNavigator by getKoin().inject()
    protected val networkManager: NetworkManager by getKoin().inject()

    private val _isOnline = MutableStateFlow(false)

    private val _viewContentState = MutableStateFlow(
        if (initialViewState is ViewState.Content) initialViewState.data else null
    )
    protected val viewContentState: StateFlow<VS?> = _viewContentState.asStateFlow()

    private enum class ViewStatus { INITIAL, LOADING, ERROR, CONTENT }

    private val _viewStatus = MutableStateFlow(
        if (initialViewState is ViewState.Content) ViewStatus.CONTENT else ViewStatus.INITIAL
    )
    private val viewStatus = _viewStatus.asStateFlow()

    val viewState: StateFlow<ViewState<VS>> = combine(
        viewContentState,
        viewStatus
    ) { content, status ->
        when (status) {
            ViewStatus.INITIAL -> initialViewState
            ViewStatus.LOADING -> ViewState.Loading
            ViewStatus.ERROR -> ViewState.Error
            ViewStatus.CONTENT -> {
                if (content != null) ViewState.Content(content) else initialViewState
            }
        }
    }.stateInWhileSubscribed(
        initialValue = initialViewState,
        onStart = {
            isUiSubscribed = true
            if (autoReloadData) {
                onStartAction()
            } else if (!hasLoadedInitialData) {
                onStartAction()
                hasLoadedInitialData = true
            }
        },
        onCompletion = {
            isUiSubscribed = false
        },
        stopTimeoutMillis = viewStateStopTimeoutMillis,
    )

    private val _viewEvent = MutableSharedFlow<VE>()
    val viewEvent = _viewEvent.asSharedFlow()

    private val _snackbarEvent = Channel<SnackbarEvent>(capacity = Channel.BUFFERED)
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    private var networkStateInitialized = false

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

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal var isUiSubscribed = false

    open fun onAction(action: VA) {}

    open fun navigateBack() {
        viewModelScope.launch {
            viewModelNavigator.pop()
        }
    }

    protected open val startAction: (() -> Unit)? get() = null
    internal fun onStartAction(manualStart: Boolean = false) {
        // Check isUiSubscribed to avoid double start action
        if (autoReloadData && manualStart && !isUiSubscribed) return
        startAction?.invoke()
    }

    protected fun setContentState(data: VS) {
        _viewContentState.value = data
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal fun updateContentState(transformation: VS.() -> VS) {
        if (viewContentState.value != null) {
            _viewContentState.update { currentViewState ->
                currentViewState?.transformation() ?: currentViewState
            }
        } else {
            // This should never have happened
//            Log.e(javaClass.name, "Action skipped: content state is null and can not be updated")
        }
    }

    protected fun currentContentState(): VS = requireNotNull(viewContentState.value)

    protected fun withContentState(action: VS.() -> Unit) {
        val state = viewContentState.value
        if (state != null) {
            action(state)
        } else {
            // This should never have happened
//            Log.e(javaClass.name, "Action skipped: content state is null and can not be used")
        }
    }

    protected open fun navigateToStartingRoute() {
        viewModelScope.launch {
            viewModelNavigator.popToRoot()
        }
    }

    protected fun showLoadingView() {
        _viewStatus.value = ViewStatus.LOADING
    }

    protected fun showErrorView() {
        _viewStatus.value = ViewStatus.ERROR
    }

    protected fun showContentView() {
        _viewStatus.value = ViewStatus.CONTENT
    }

    protected fun showSnackbar(snackbarEvent: SnackbarEvent) {
        _snackbarEvent.trySend(snackbarEvent)
    }

    protected fun showErrorSnackbar(message: UiText) {
        showSnackbar(SnackbarEvent.Error(message = message))
    }

    protected fun showSuccessSnackbar(message: UiText) {
        showSnackbar(SnackbarEvent.Success(message = message))
    }

    protected fun emitEvent(event: VE) {
        viewModelScope.launch {
            _viewEvent.emit(event)
        }
    }
}