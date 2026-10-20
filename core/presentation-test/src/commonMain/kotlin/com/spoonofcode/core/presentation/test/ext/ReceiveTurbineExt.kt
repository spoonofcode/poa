package com.spoonofcode.core.presentation.test.ext

import app.cash.turbine.ReceiveTurbine
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.core.presentation.base.ViewState

suspend fun <VS : BaseViewState> ReceiveTurbine<ViewState<VS>>.awaitInitialState() {
    val state = awaitItem()
    if (state !is ViewState.Initial) {
        throw AssertionError(
            "Expected NewViewState.Initial, but received: ${state::class.simpleName}"
        )
    }
}

suspend fun <VS : BaseViewState> ReceiveTurbine<ViewState<VS>>.awaitLoadingState() {
    val state = awaitItem()
    if (state !is ViewState.Loading) {
        throw AssertionError(
            "Expected NewViewState.Loading, but received: ${state::class.simpleName}"
        )
    }
}

suspend fun <VS : BaseViewState> ReceiveTurbine<ViewState<VS>>.awaitErrorState() {
    val state = awaitItem()
    if (state !is ViewState.Error) {
        throw AssertionError(
            "Expected NewViewState.Error, but received: ${state::class.simpleName}"
        )
    }
}

suspend inline fun <reified VS : BaseViewState> ReceiveTurbine<ViewState<VS>>.awaitContentState(): VS {
    val state = awaitItem()
    return if (state is ViewState.Content<VS>) {
        state.data
    } else {
        throw AssertionError(
            "Expected NewViewState.Content with data type ${VS::class.simpleName}, " +
                    "but received: ${state::class.simpleName}"
        )
    }
}