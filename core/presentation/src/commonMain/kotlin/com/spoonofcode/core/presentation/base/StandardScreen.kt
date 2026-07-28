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
    topBarActions: @Composable (viewState: VS, onAction: (VA) -> Unit) -> List<TopBarAction> = { _, _ -> emptyList() },
    bottomBar: @Composable (viewState: VS, onAction: (VA) -> Unit) -> Unit = { _, _ -> },
    dialogs: @Composable (viewState: VS, onAction: (VA) -> Unit) -> Unit = { _, _ -> },
    bottomSheets: @Composable (viewState: VS, onAction: (VA) -> Unit) -> Unit = { _, _ -> },
    content: @Composable ColumnScope.(viewState: VS, onAction: (VA) -> Unit) -> Unit
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    setSnackbarHostState(snackbarHostState, viewModel.snackbarEvent)

    StandardScaffold(
        viewState = viewState,
        onAction = viewModel::onAction,
        onStartAction = viewModel::onStartAction,
        navigateBack = viewModel::navigateBack,
        topBarTitle = topBarTitle,
        backNavigationEnable = backNavigationEnable,
        verticalScrollEnable = verticalScrollEnable,
        respectScaffoldImePadding = respectScaffoldImePadding,
        customBottomScaffoldInnerPadding = customBottomScaffoldInnerPadding,
        contentBackgroundColor = contentBackgroundColor,
        provideContentPadding = provideContentPadding,
        navigationBackAction = navigationBackAction,
        topBarActions = topBarActions,
        bottomBar = bottomBar,
        dialogs = dialogs,
        bottomSheets = bottomSheets,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(snackbarData = it)
            }
        },
        content = content
    )
}

@Composable
private fun <VS : BaseViewState, VA : BaseViewAction> StandardScaffold(
    viewState: ViewState<VS>,
    onAction: (VA) -> Unit,
    onStartAction: () -> Unit,
    navigateBack: () -> Unit,
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
    topBarActions: @Composable (viewState: VS, onAction: (VA) -> Unit) -> List<TopBarAction> = { _, _ -> emptyList() },
    bottomBar: @Composable (viewState: VS, onAction: (VA) -> Unit) -> Unit = { _, _ -> },
    dialogs: @Composable (viewState: VS, onAction: (VA) -> Unit) -> Unit = { _, _ -> },
    bottomSheets: @Composable (viewState: VS, onAction: (VA) -> Unit) -> Unit = { _, _ -> },
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable ColumnScope.(viewState: VS, onAction: (VA) -> Unit) -> Unit
) {
    val navBackAction = remember(navigationBackAction, onAction, navigateBack) {
        navigationBackAction?.invoke(onAction) ?: { navigateBack() }
    }

    Scaffold(
        modifier = Modifier
            .addIf(respectScaffoldImePadding) { imePadding() }
            .fillMaxSize(),
        topBar = {
            val state = viewState
            if (state is ViewState.Content) {
                TopBar(
                    backNavigationEnable = backNavigationEnable,
                    topAppBarTitle = topBarTitle ?: "",
                    navigationBackAction = navBackAction,
                    iconBarActions = topBarActions(
                        state.data,
                        onAction
                    )
                )
            }
        },
        bottomBar = {
            (viewState as? ViewState.Content)?.data?.let {
                bottomBar(it, onAction)
            }
        },
        snackbarHost = snackbarHost
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
                ViewState.Error -> ErrorView(reload = { onStartAction() })
                is ViewState.Content -> {
                    dialogs(state.data, onAction)
                    bottomSheets(state.data, onAction)

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
                        content(state.data, onAction)
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
        StandardScaffold<VS, BaseViewAction>(
            viewState = ViewState.Content(viewState),
            onAction = {},
            onStartAction = {},
            navigateBack = {},
            topBarTitle = title,
            backNavigationEnable = backNavigationEnable,
            provideContentPadding = provideContentPadding,
            content = { vs, _ -> content(vs) }
        )
    }
}