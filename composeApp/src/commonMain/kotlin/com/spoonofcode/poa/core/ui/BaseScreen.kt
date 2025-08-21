package com.spoonofcode.poa.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.spoonofcode.poa.core.ui.compose.ErrorView
import com.spoonofcode.poa.core.ui.compose.LoadingView
import com.spoonofcode.poa.core.ui.compose.Snackbar
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.core.ui.compose.setSnackbarHostState
import com.spoonofcode.poa.core.ui.ext.addIf
import com.spoonofcode.poa.core.ui.ext.viewEnable
import com.spoonofcode.poa.core.ui.navigation.NavigationHandler
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

abstract class BaseScreen<VM : BaseViewModel<VS>, VS : BaseViewState>(
    protected open val backNavigationEnable: Boolean = true,
    protected open val verticalScrollEnable: Boolean = true,
) : Screen {

    protected open fun provideNavigationBackAction(viewModel: VM): () -> Unit =
        { viewModel.navigateBack() }

    protected open fun provideTopAppBarTitle(): StringResource? = null

    protected open fun provideContentPadding(): PaddingValues =
        PaddingValues(Paddings.screenPadding)

    protected open fun provideTopBarActions(
        viewModel: VM?, // Is nullable because we need this for previews
    ): List<TopBarAction> = emptyList()

    protected open fun provideReloadAction(viewModel: VM): () -> Unit = {}

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

        val topBarActions = provideTopBarActions(viewModel)
        val reloadAction = remember { provideReloadAction(viewModel) }

        val dialogs = provideDialogs(
            viewModel = viewModel,
            viewState = viewState,
        )

        val content = provideContent(
            viewModel = viewModel,
            viewState = viewState,
        )


        NavigationHandler(viewModel.navigationFlow, navigator)
        setSnackbarHostState(snackbarHostState, viewModel.snackbarEvent)

        ContentView(
            snackbarHostState = snackbarHostState,
            topAppBarTitle = provideTopAppBarTitle(),
            backNavigationEnable = backNavigationEnable,
            topBarActions = topBarActions,
            reloadAction = reloadAction,
            verticalScrollEnable = verticalScrollEnable,
            contentPadding = provideContentPadding(),
            navigationBackAction = { navigator.pop() },
            screenState = viewState.screenState,
            dialogs = dialogs,
            content = content,
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ContentView(
        snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
        topAppBarTitle: StringResource? = provideTopAppBarTitle(),
        backNavigationEnable: Boolean = false,
        topBarActions: List<TopBarAction>,
        reloadAction: () -> Unit = {},
        verticalScrollEnable: Boolean = false,
        contentPadding: PaddingValues = provideContentPadding(),
        navigationBackAction: () -> Unit = {},
        screenState: ScreenState,
        dialogs: @Composable (() -> Unit),
        content: @Composable ColumnScope.() -> Unit,
    ) {

        dialogs()

        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    snackbar = { snackbarData -> Snackbar(snackbarData) }
                )
            },
            topBar = {
                if (topAppBarTitle != null) {
                    CenterAlignedTopAppBar(
                        title = { Texts.TM(stringResource(resource = topAppBarTitle)) },
                        navigationIcon = if (backNavigationEnable) {
                            {
                                IconButton(onClick = navigationBackAction) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        } else {
                            {}
                        },
                        actions = {
                            topBarActions.forEach { spec ->
                                IconButton(onClick = spec.onClick) {
                                    Icon(
                                        imageVector = spec.icon,
                                        contentDescription = spec.description
                                    )
                                }
                            }
                        }
                    )
                }
            },
            modifier = Modifier.viewEnable(screenState != ScreenState.LOADING).fillMaxSize()
        ) { innerPadding ->
            when (screenState) {
                ScreenState.INITIAL -> {}
                ScreenState.LOADING -> LoadingView()
                ScreenState.ERROR -> ErrorView(reload = reloadAction)
                ScreenState.CONTENT -> Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .addIf(verticalScrollEnable) { verticalScroll(rememberScrollState()) }
                        .padding(innerPadding)
                        .padding(contentPadding),
                    content = content
                )
            }
        }
    }

    @Composable
    fun PreviewContent(
        viewState: VS,
    ) {
        ContentView(
            topBarActions = provideTopBarActions(viewModel = null),
            screenState = viewState.screenState,
            dialogs = provideDialogs(viewModel = null, viewState = viewState),
            content = provideContent(viewModel = null, viewState = viewState),
        )
    }

    @Immutable
    data class TopBarAction(
        val icon: ImageVector,
        val description: String,
        val onClick: () -> Unit
    )
}