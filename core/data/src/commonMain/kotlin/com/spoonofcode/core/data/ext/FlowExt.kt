package com.spoonofcode.core.data.ext

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration.Companion.milliseconds

/**
 * Default ANR (Application Not Responding) timeout threshold.
 *
 * [Documentation](https://developer.android.com/topic/performance/vitals/anr)
 */
const val DEFAULT_STOP_TIMEOUT_MILLIS = 5000L

private const val DEBOUNCE_DELAY_MS = 500L

/**
 * Converts a standard [Flow] into a hot [StateFlow], encapsulating boilerplate lifecycle
 * management, state updating, conditional error logging, and subscription behavior.
 *
 * This extension function unifies common ViewModel behavior for managing reactive states:
 * 1. Triggers the optional [onStart] hook whenever the flow collection starts (e.g., to show a loader).
 * 2. Routes every incoming state emission directly to the [onStateChanged] callback.
 * 3. Intercepts flow completion: automatically logs unexpected errors to Logcat in English,
 * while forwarding successful completions or routine cancellations to the [onCompletion] hook.
 * 4. Configures sharing using [SharingStarted.WhileSubscribed] to efficiently survive configuration changes.
 *
 * @param initialValue The initial state of the flow.
 * @param stopTimeoutMillis The timeout in milliseconds before the upstream flow is stopped
 * after the last collector disappears. This delay is crucial for handling configuration changes
 * (like screen rotation), ensuring that the StateFlow doesn't restart its work if the UI is recreated
 * within this timeframe.
 * @param onStart A suspend lambda triggered when the flow collection starts. Defaults to an empty action.
 * @param onCompletion A suspend lambda triggered when the flow completes successfully or is cancelled
 * routinely. This action is bypassed if the flow terminates due to an unexpected error. Defaults to an empty action.
 * @param onStateChanged A suspend lambda triggered on every new state emission to handle or process the update.
 */
context(viewModel: ViewModel)
fun <T> Flow<T>.stateInWhileSubscribed(
    initialValue: T,
    stopTimeoutMillis: Long = DEFAULT_STOP_TIMEOUT_MILLIS,
    onStart: suspend () -> Unit = {},
    onStateChanged: suspend (T) -> Unit = {},
    onCompletion: suspend (cause: Throwable?) -> Unit = {}, // Twoja własna akcja
): StateFlow<T> = this
    .onStart { onStart() }
    .onEach { state ->
        Log.d(javaClass.name, "New flow state: $state")
        onStateChanged(state)
    }
    .onCompletion { cause ->
        if (cause != null && cause !is CancellationException) {
            Log.e(
                javaClass.name,
                "Flow completed with an unexpected error: ${cause.message}",
                cause
            )
        } else {
            onCompletion(cause)
        }
    }
    .stateIn(
        scope = viewModel.viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis),
        initialValue = initialValue
    )


context(viewModel: ViewModel)
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
fun <VS : Any, P, R> Flow<VS>.observeSearch(
    debounceDelayMs: Long = DEBOUNCE_DELAY_MS,
    extractQuery: (VS) -> String,
    extractFilters: (VS) -> P,
    onLoading: () -> Unit,
    executeSearch: suspend (query: String, params: P) -> R,
    onResult: (R) -> Unit
): Job {
    var isFirstEmission = true
    var lastQuery = ""

    return viewModel.viewModelScope.launch {
        this@observeSearch
            .map { state -> extractQuery(state) to extractFilters(state) }
            .distinctUntilChanged()
            .debounce { (query, _) ->
                val queryChanged = query != lastQuery
                lastQuery = query

                when {
                    isFirstEmission -> {
                        isFirstEmission = false
                        0L.milliseconds
                    }

                    queryChanged -> debounceDelayMs.milliseconds
                    else -> 0L.milliseconds
                }
            }
            .flatMapLatest { (query, params) ->
                flow {
                    emit(executeSearch(query, params))
                }.onStart {
                    onLoading()
                }
            }
            .collect { result ->
                onResult(result)
            }
    }
}