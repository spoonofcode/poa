package com.spoonofcode.core.presentation.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.spoonofcode.core.presentation.compose.ErrorView
import com.spoonofcode.core.presentation.compose.LoadingView
import com.spoonofcode.core.presentation.compose.Paddings
import com.spoonofcode.core.presentation.compose.TopBar
import com.spoonofcode.core.presentation.compose.TopBarAction
import com.spoonofcode.core.presentation.compose.snackbar.Snackbar
import com.spoonofcode.core.presentation.compose.snackbar.setSnackbarHostState
import com.spoonofcode.core.presentation.ext.addIf
import com.spoonofcode.core.presentation.ext.viewEnable
import com.spoonofcode.core.presentation.navigation.NavigationHandler
import com.spoonofcode.core.presentation.theme.AppTheme
import org.jetbrains.compose.resources.StringResource

abstract class BaseScreen<VM : BaseViewModel<VS, VA>, VS : BaseViewState, VA : BaseViewAction>(
    protected open val backNavigationEnable: Boolean = true,
    protected open val verticalScrollEnable: Boolean = true,
) : Screen {

    protected open fun provideNavigationBackAction(
        onAction: (VA) -> Unit,
    ): (() -> Unit)? = null

    protected open fun provideTopAppBarTitle(): StringResource? = null

    protected open fun provideContentPadding(): PaddingValues =
        PaddingValues(
            start = Paddings.screenPadding,
            end = Paddings.screenPadding,
            bottom = Paddings.screenPadding,
        )

    protected open fun provideTopBarActions(
        viewState: VS,
        onAction: (VA) -> Unit,
    ): List<TopBarAction> = emptyList()

    protected open fun provideInitAction(
        onAction: (VA) -> Unit,
    ): () -> Unit = {}

    // By Default Reload Action is Init Action
    protected open fun provideReloadAction(
        onAction: (VA) -> Unit,
    ): () -> Unit = provideInitAction(onAction)

    @Composable
    protected open fun provideDialogs(
        viewState: VS,
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
    override fun Content() {
        val navigator: Navigator =
            LocalNavigator.currentOrThrow.parent ?: LocalNavigator.currentOrThrow

        val snackbarHostState = remember { SnackbarHostState() }
        val viewModel = provideViewModel()
        val uiState by viewModel.viewState.collectAsStateWithLifecycle()

        NavigationHandler(viewModel.navigationFlow, navigator)
        setSnackbarHostState(snackbarHostState, viewModel.snackbarEvent)

        LaunchedEffect(true) {
            provideInitAction(viewModel::onAction).invoke()
        }

        val navigationBackAction = provideNavigationBackAction(viewModel::onAction)
            ?: { viewModel.navigateBack() }

        ContentView(
            snackbarHostState = snackbarHostState,
            viewState = uiState.data,
            screenState = uiState.screenState,
            navigationBackAction = navigationBackAction,
            onAction = viewModel::onAction

        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ContentView(
        snackbarHostState: SnackbarHostState,
        viewState: VS,
        screenState: ScreenState,
        navigationBackAction: () -> Unit,
        onAction: (VA) -> Unit,
    ) {
        provideDialogs(
            viewState = viewState,
            onAction = onAction,
        )

        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    snackbar = { snackbarData -> Snackbar(snackbarData) }
                )
            },
            topBar = {
                val topAppBarTitle = provideTopAppBarTitle()
                if (topAppBarTitle != null) {
                    TopBar(
                        backNavigationEnable = backNavigationEnable,
                        topAppBarTitle = topAppBarTitle,
                        navigationBackAction = navigationBackAction,
                        iconBarActions = provideTopBarActions(viewState, onAction)
                    )
                }
            },
            modifier = Modifier.viewEnable(screenState != ScreenState.LOADING)
                .fillMaxSize()
        ) { innerPadding ->
            when (screenState) {
                ScreenState.LOADING -> LoadingView()
                ScreenState.ERROR -> ErrorView(reload = provideReloadAction(onAction))
                ScreenState.CONTENT -> Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .addIf(verticalScrollEnable) { verticalScroll(rememberScrollState()) }
                        .padding(innerPadding)
                        .padding(provideContentPadding()),
                    verticalArrangement = Arrangement.spacedBy(Paddings.fieldsPadding),
                    content = provideContent(
                        viewState = viewState,
                        onAction = onAction,
                    )
                )
            }
        }
    }

    @Composable
    fun PreviewContent(
        viewState: VS,
        screenState: ScreenState = ScreenState.CONTENT,
    ) {
        AppTheme {
            val snackbarHostState = remember { SnackbarHostState() }
            ContentView(
                snackbarHostState = snackbarHostState,
                viewState = viewState,
                screenState = screenState,
                navigationBackAction = {},
                onAction = {},
            )
        }
    }
}