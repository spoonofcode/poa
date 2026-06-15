package com.spoonofcode.core.presentation.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.internal.BackHandler
import com.spoonofcode.core.presentation.compose.ErrorView
import com.spoonofcode.core.presentation.compose.LoadingView
import com.spoonofcode.core.presentation.compose.Paddings
import com.spoonofcode.core.presentation.compose.TopBar
import com.spoonofcode.core.presentation.compose.TopBarAction
import com.spoonofcode.core.presentation.compose.snackbar.Snackbar
import com.spoonofcode.core.presentation.compose.snackbar.setSnackbarHostState
import com.spoonofcode.core.presentation.ext.addIf
import com.spoonofcode.core.presentation.theme.AppTheme
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseScreen<VM : BaseViewModel<VS, VA, VE>, VS : BaseViewState, VA : BaseViewAction, VE : BaseViewEvent>(
    protected open val backNavigationEnable: Boolean = true,
    protected open val backOSButtonEnable: Boolean = true,
    protected open val verticalScrollEnable: Boolean = true,
    protected open val snackbarBottomExtraPadding: Dp = 0.dp,
    protected open val defaultTopBarTopPadding: Dp = 20.dp,
    protected open val respectScaffoldImePadding: Boolean = false,
    protected open val customBottomScaffoldInnerPadding: Dp? = null,
) {
    protected open fun provideContentPadding(): PaddingValues = PaddingValues(
        start = Paddings.screenPadding,
        end = Paddings.screenPadding,
        bottom = Paddings.screenPadding,
    )


    protected open fun provideNavigationBackIcon(): ImageVector =
        Icons.AutoMirrored.Filled.ArrowBack

    protected open fun provideNavigationBackAction(
        onAction: (VA) -> Unit,
    ): (() -> Unit)? = null

    @Composable
    protected open fun provideTopAppBarTitle(): String? = null

    @Composable
    protected open fun provideTopBar(
        viewState: ViewState<VS>,
        onAction: (VA) -> Unit,
        navigationBackAction: () -> Unit,
    ) {
        Column(modifier = Modifier.padding(vertical = defaultTopBarTopPadding)) {
            TopBar(
                backNavigationEnable = backNavigationEnable,
                topAppBarTitle = provideTopAppBarTitle(),
                navigationBackAction = navigationBackAction,
                navigationBackIcon = provideNavigationBackIcon(),
                colors = provideTopBarColors(),
                iconBarActions = provideTopBarActions(onAction)
            )
        }
    }


    @Composable
    protected open fun provideTopBarColors(): TopAppBarColors = TopAppBarDefaults.topAppBarColors()

    @Composable
    protected open fun provideContentBackgroundColor(): Color = MaterialTheme.colorScheme.background

    @Composable
    protected open fun provideTopBarActions(
        onAction: (VA) -> Unit,
    ): List<TopBarAction> = emptyList()

    @Composable
    protected open fun provideLoadingView() {
        LoadingView()
    }

    @Composable
    protected open fun provideDialogs(
        viewState: VS,
        onAction: (VA) -> Unit,
    ) {
    }

    @Composable
    protected open fun provideBottomSheets(
        viewState: VS,
        onAction: (VA) -> Unit,
    ) {
    }

    @Composable
    protected open fun provideBottomBar(
        viewState: VS,
        onAction: (VA) -> Unit,
    ) {
    }

    @Composable
    protected open fun provideViewEvents(
        viewEvent: SharedFlow<VE>,
        onAction: (VA) -> Unit,
    ) {
    }

    @Composable
    protected abstract fun provideViewModel(): VM

    @Composable
    protected abstract fun provideContent(
        viewState: VS,
        onAction: (VA) -> Unit,
    ): @Composable ColumnScope.() -> Unit

    @Composable
    fun Content() {
        val snackbarHostState = remember { SnackbarHostState() }
        val viewModel = provideViewModel()

        // TODO Fix networkState
        // viewModel.networkState.collectAsStateWithLifecycle()

        val viewState by viewModel.viewState.collectAsStateWithLifecycle()

        val customBackAction = provideNavigationBackAction(viewModel::onAction)
        val navigationBackAction = customBackAction ?: { viewModel.navigateBack() }

        BackHandler(true) {
            if (backOSButtonEnable) {
                navigationBackAction()
            }
        }

        setSnackbarHostState(snackbarHostState, viewModel.snackbarEvent)

        provideViewEvents(viewModel.viewEvent, viewModel::onAction)

        HandleSessionEvents(
            onExtendSession = { viewModel.extendSession() },
            onLogoutNow = { viewModel.logoutNow() })

        ContentView(
            snackbarHostState = snackbarHostState,
            viewState = viewState,
            reloadAction = { viewModel.onStartAction() },
            navigationBackAction = navigationBackAction,
            onAction = viewModel::onAction
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ContentView(
        snackbarHostState: SnackbarHostState,
        viewState: ViewState<VS>,
        reloadAction: () -> Unit,
        navigationBackAction: () -> Unit,
        onAction: (VA) -> Unit,
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState, snackbar = { snackbarData ->
                        Snackbar(
                            snackbarData = snackbarData,
                            bottomExtraPadding = snackbarBottomExtraPadding
                        )
                    })
            },
            topBar = {
                provideTopBar(
                    viewState = viewState,
                    onAction = onAction,
                    navigationBackAction = navigationBackAction,
                )
            },
            bottomBar = {
                if (viewState is ViewState.Content) {
                    provideBottomBar(viewState.data, onAction)
                }
            },
            modifier = Modifier
                .addIf(respectScaffoldImePadding) { imePadding() }
                .fillMaxSize(),
            content = { innerPadding ->
                val layoutDirection = LocalLayoutDirection.current
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = innerPadding.calculateLeftPadding(layoutDirection),
                            top = innerPadding.calculateTopPadding(),
                            end = innerPadding.calculateEndPadding(layoutDirection),
                            bottom = customBottomScaffoldInnerPadding
                                ?: innerPadding.calculateBottomPadding()
                        )
                ) {
                    when (viewState) {
                        ViewState.Initial -> {}
                        ViewState.Loading -> provideLoadingView()
                        ViewState.Error -> ErrorView(reload = reloadAction)
                        is ViewState.Content -> ContentWrapper(viewState.data, onAction)
                    }
                }
            }
        )
    }

    @Composable
    private fun ColumnScope.ContentWrapper(
        viewState: VS,
        onAction: (VA) -> Unit
    ) {
        provideDialogs(
            viewState = viewState,
            onAction = onAction,
        )

        provideBottomSheets(
            viewState = viewState,
            onAction = onAction,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(provideContentBackgroundColor())
                .addIf(verticalScrollEnable) {
                    verticalScroll(
                        rememberScrollState()
                    )
                }
                .padding(provideContentPadding()),
            verticalArrangement = Arrangement.spacedBy(Paddings.fieldsPadding),
            content = provideContent(
                viewState = viewState,
                onAction = onAction,
            )
        )
    }

    @Composable
    fun PreviewContent(
        viewState: VS,
    ) {
        AppTheme {
            val snackbarHostState = remember { SnackbarHostState() }
            ContentView(
                snackbarHostState = snackbarHostState,
                viewState = ViewState.Content(viewState),
                reloadAction = {},
                navigationBackAction = {},
                onAction = {},
            )
        }
    }
}