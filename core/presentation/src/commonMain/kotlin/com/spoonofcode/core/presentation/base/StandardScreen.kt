package com.spoonofcode.core.presentation.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.spoonofcode.core.presentation.compose.ErrorView
import com.spoonofcode.core.presentation.compose.LoadingView
import com.spoonofcode.core.presentation.compose.Paddings
import com.spoonofcode.core.presentation.compose.TopBar
import com.spoonofcode.core.presentation.compose.TopBarAction
import com.spoonofcode.core.presentation.compose.snackbar.setSnackbarHostState
import com.spoonofcode.core.presentation.ext.addIf
import com.spoonofcode.core.presentation.theme.AppTheme

@Composable
fun <VM : BaseViewModel<VS, VA, VE>, VS : BaseViewState, VA : BaseViewAction, VE : BaseViewEvent> StandardScreen(
    viewModel: VM,
    topBarTitle: String? = null,
    backNavigationEnable: Boolean = true,
    verticalScrollEnable: Boolean = true,
    respectScaffoldImePadding: Boolean = false,
    customBottomScaffoldInnerPadding: Dp? = null,
    contentBackgroundColor: Color = MaterialTheme.colorScheme.background,
    provideContentPadding: () -> PaddingValues = {
        PaddingValues(
            start = Paddings.screenPadding,
            end = Paddings.screenPadding,
            bottom = Paddings.screenPadding,
        )
    },
    navigationBackAction: ((onAction: (VA) -> Unit) -> (() -> Unit))? = null,
    topBarActions: @Composable (onAction: (VA) -> Unit) -> List<TopBarAction> = { emptyList() },
    bottomBar: @Composable (viewState: VS, onAction: (VA) -> Unit) -> Unit = { _, _ -> },
    dialogs: @Composable (viewState: VS, onAction: (VA) -> Unit) -> Unit = { _, _ -> },
    bottomSheets: @Composable (viewState: VS, onAction: (VA) -> Unit) -> Unit = { _, _ -> },
    content: @Composable ColumnScope.(viewState: VS, onAction: (VA) -> Unit) -> Unit
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    setSnackbarHostState(snackbarHostState, viewModel.snackbarEvent)

    val navBackAction = remember(navigationBackAction, viewModel) {
        navigationBackAction?.invoke(viewModel::onAction) ?: { viewModel.navigateBack() }
    }

    Scaffold(
        modifier = Modifier
            .addIf(respectScaffoldImePadding) { imePadding() }
            .fillMaxSize(),
        topBar = {
            TopBar(
                backNavigationEnable = backNavigationEnable,
                topAppBarTitle = topBarTitle ?: "",
                navigationBackAction = navBackAction,
                iconBarActions = topBarActions(viewModel::onAction)
            )
        },
        bottomBar = {
            (viewState as? ViewState.Content)?.data?.let {
                bottomBar(it, viewModel::onAction)
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(snackbarData = it)
            }
        }
    ) { innerPadding ->
        val layoutDirection = LocalLayoutDirection.current
        Box(
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(layoutDirection),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(layoutDirection),
                    bottom = customBottomScaffoldInnerPadding
                        ?: innerPadding.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {
            when (val state = viewState) {
                ViewState.Initial -> {}
                ViewState.Loading -> LoadingView()
                ViewState.Error -> ErrorView(reload = { viewModel.onStartAction() })
                is ViewState.Content -> {
                    dialogs(state.data, viewModel::onAction)
                    bottomSheets(state.data, viewModel::onAction)

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(contentBackgroundColor)
                            .addIf(verticalScrollEnable) {
                                verticalScroll(rememberScrollState())
                            }
                            .padding(provideContentPadding()),
                        verticalArrangement = Arrangement.spacedBy(Paddings.fieldsPadding)
                    ) {
                        content(state.data, viewModel::onAction)
                    }
                }
            }
        }
    }
}

@Composable
fun <VS : BaseViewState> StandardScreenPreview(
    viewState: VS,
    title: String = "",
    backNavigationEnable: Boolean = true,
    provideContentPadding: () -> PaddingValues = {
        PaddingValues(
            start = Paddings.screenPadding,
            end = Paddings.screenPadding,
            bottom = Paddings.screenPadding,
        )
    },
    content: @Composable ColumnScope.(VS) -> Unit
) {
    AppTheme {
        Scaffold(
            topBar = {
                TopBar(
                    backNavigationEnable = backNavigationEnable,
                    topAppBarTitle = title,
                    navigationBackAction = {},
                    iconBarActions = emptyList()
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(provideContentPadding()),
                    verticalArrangement = Arrangement.spacedBy(Paddings.fieldsPadding)
                ) {
                    content(viewState)
                }
            }
        }
    }
}