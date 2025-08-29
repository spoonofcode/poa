package com.spoonofcode.poa.core.ui

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.spoonofcode.poa.core.ui.compose.ErrorView
import com.spoonofcode.poa.core.ui.compose.LoadingView
import com.spoonofcode.poa.core.ui.compose.Snackbar
import com.spoonofcode.poa.core.ui.compose.TopBar
import com.spoonofcode.poa.core.ui.compose.TopBarAction
import com.spoonofcode.poa.core.ui.compose.setSnackbarHostState
import com.spoonofcode.poa.core.ui.ext.addIf
import com.spoonofcode.poa.core.ui.ext.viewEnable
import com.spoonofcode.poa.core.ui.navigation.NavigationHandler
import org.jetbrains.compose.resources.StringResource

abstract class BaseScreen<VM : BaseViewModel<VS>, VS : BaseViewState>(
    protected open val backNavigationEnable: Boolean = true,
    protected open val verticalScrollEnable: Boolean = true,
) : Screen {

    protected open fun provideNavigationBackAction(viewModel: VM?): () -> Unit =
        { viewModel?.navigateBack() }

    protected open fun provideTopAppBarTitle(): StringResource? = null

    protected open fun provideContentPadding(): PaddingValues =
        PaddingValues(Paddings.screenPadding)

    protected open fun provideTopBarActions(
        viewModel: VM?, // Is nullable because we need this for previews
    ): List<TopBarAction> = emptyList()

    protected open fun provideInitAction(viewModel: VM?): () -> Unit = {}

    // By Default Reload Action is Init Action
    protected open fun provideReloadAction(viewModel: VM?): () -> Unit =
        provideInitAction(viewModel)

    @Composable
    protected open fun provideDialogs(
        viewModel: VM?, // Is nullable because we need this for previews
        viewState: VS,
    ): @Composable () -> Unit = {}

    @Composable
    protected abstract fun provideViewModel(): VM

    @Composable
    protected abstract fun provideContent(
        viewModel: VM?,  // Is nullable because we need this for previews
        viewState: VS,
    ): @Composable ColumnScope.() -> Unit

    @Composable
    override fun Content() {
        val navigator: Navigator =
            LocalNavigator.currentOrThrow.parent ?: LocalNavigator.currentOrThrow

        val snackbarHostState = remember { SnackbarHostState() }
        val viewModel = provideViewModel()
        val viewState by viewModel.viewState.collectAsState()

        NavigationHandler(viewModel.navigationFlow, navigator)
        setSnackbarHostState(snackbarHostState, viewModel.snackbarEvent)

        LaunchedEffect(true) {
            provideInitAction(viewModel).invoke()
        }

        ContentView(
            snackbarHostState = snackbarHostState,
            viewModel = viewModel,
            viewState = viewState,
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ContentView(
        snackbarHostState: SnackbarHostState,
        viewModel: VM?,  // Is nullable because we need this for previews
        viewState: VS,
    ) {

        provideDialogs(
            viewModel = viewModel,
            viewState = viewState,
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
                        navigationBackAction = provideNavigationBackAction(viewModel),
                        iconBarActions = provideTopBarActions(viewModel)
                    )
                }
            },
            modifier = Modifier.viewEnable(viewState.screenState != ScreenState.LOADING)
                .fillMaxSize()
        ) { innerPadding ->
            when (viewState.screenState) {
                ScreenState.LOADING -> LoadingView()
                ScreenState.ERROR -> ErrorView(reload = provideReloadAction(viewModel))
                ScreenState.CONTENT -> Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .addIf(verticalScrollEnable) { verticalScroll(rememberScrollState()) }
                        .padding(innerPadding)
                        .padding(provideContentPadding()),
                    content = provideContent(
                        viewModel = viewModel,
                        viewState = viewState,
                    )
                )
            }
        }
    }

    @Composable
    fun PreviewContent(
        viewState: VS,
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        ContentView(
            snackbarHostState = snackbarHostState,
            viewModel = null,
            viewState = viewState,
        )
    }


}