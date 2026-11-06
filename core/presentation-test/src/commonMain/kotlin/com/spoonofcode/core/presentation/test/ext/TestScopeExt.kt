package com.spoonofcode.core.presentation.test.ext

import app.cash.turbine.ReceiveTurbine
import app.cash.turbine.TurbineContext
import app.cash.turbine.turbineScope
import com.spoonofcode.core.presentation.base.BaseViewAction
import com.spoonofcode.core.presentation.base.BaseViewEvent
import com.spoonofcode.core.presentation.base.BaseViewModel
import com.spoonofcode.core.presentation.base.BaseViewState
import com.spoonofcode.core.presentation.base.ViewState
import com.spoonofcode.core.presentation.compose.snackbar.SnackbarEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle

class VmTestScope<VS : BaseViewState, VA : BaseViewAction, VE : BaseViewEvent, VM : BaseViewModel<VS, VA, VE>>(
    val viewModel: VM,
    val viewState: ReceiveTurbine<ViewState<VS>>,
    val viewEvent: ReceiveTurbine<VE>,
    val snackbarEvent: ReceiveTurbine<SnackbarEvent>,
    private val turbineContext: TurbineContext,
    private val backgroundScope: CoroutineScope,
    private val testScheduler: TestCoroutineScheduler,
) {
    fun <T> Flow<T>.test(): ReceiveTurbine<T> =
        with(turbineContext) {
            testIn(backgroundScope)
        }

    fun onAction(action: VA) {
        viewModel.onAction(action)
        testScheduler.advanceUntilIdle()
    }

    fun updateContentState(reducer: VS.() -> VS) {
        viewModel.updateContentState(reducer)
    }
}

suspend fun <VS : BaseViewState, VA : BaseViewAction, VE : BaseViewEvent, VM : BaseViewModel<VS, VA, VE>> TestScope.runVmTest(
    viewModel: VM,
    expectMostRecentItem: Boolean = true,
    block: suspend VmTestScope<VS, VA, VE, VM>.() -> Unit,
) {
    turbineScope {
        val viewStateTurbine = viewModel.viewState.testIn(backgroundScope)
        val viewEventTurbine = viewModel.viewEvent.testIn(backgroundScope)
        val snackBarEventTurbine = viewModel.snackbarEvent.testIn(backgroundScope)

        if (expectMostRecentItem) {
            testScheduler.advanceUntilIdle()
            viewStateTurbine.expectMostRecentItem()
        }

        val scope =
            VmTestScope(
                viewModel = viewModel,
                viewState = viewStateTurbine,
                viewEvent = viewEventTurbine,
                snackbarEvent = snackBarEventTurbine,
                turbineContext = this,
                backgroundScope = backgroundScope,
                testScheduler = testScheduler,
            )

        scope.block()

        viewStateTurbine.ensureAllEventsConsumed()
        viewEventTurbine.ensureAllEventsConsumed()
        snackBarEventTurbine.ensureAllEventsConsumed()
    }
}
