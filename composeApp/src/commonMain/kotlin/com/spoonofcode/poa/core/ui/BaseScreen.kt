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

    protected open fun provideTopAppBarTitle(): StringResource? = null

    protected open fun provideContentPadding(): PaddingValues =
        PaddingValues(Paddings.screenPadding)

    protected open fun provideTopBarActions(viewModel: VM): List<TopBarAction> = emptyList()

    protected open fun provideReloadAction(viewModel: VM): () -> Unit = {}

    @Composable
    protected abstract fun provideViewModel(): VM

    @Composable
    protected abstract fun provideContentView(
        viewModel: VM,
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
        val reloadScreen = remember { provideReloadAction(viewModel) }

        NavigationHandler(viewModel.navigationFlow, navigator)
        setSnackbarHostState(snackbarHostState, viewModel.snackbarEvent)

        ContentView(
            snackbarHostState = snackbarHostState,
            screenTopAppBarTitle = provideTopAppBarTitle(),
            backNavigationEnable = backNavigationEnable,
            topBarActions = topBarActions,
            reloadScreen = reloadScreen,
            verticalScrollEnable = verticalScrollEnable,
            contentPadding = provideContentPadding(),
            onBackClick = { navigator.pop() },
            isLoadingView = viewState.isLoadingView,
            iErrorView = viewState.isErrorView,
            content = provideContentView(
                viewModel = viewModel,
                viewState = viewState,
            )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ContentView(
        snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
        screenTopAppBarTitle: StringResource? = provideTopAppBarTitle(),
        backNavigationEnable: Boolean = false,
        topBarActions: List<TopBarAction> = emptyList(),
        reloadScreen: () -> Unit = {},
        verticalScrollEnable: Boolean = false,
        contentPadding: PaddingValues = provideContentPadding(),
        onBackClick: () -> Unit = {},
        isLoadingView: Boolean = false,
        iErrorView: Boolean = false,
        content: @Composable ColumnScope.() -> Unit,
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    snackbar = { snackbarData -> Snackbar(snackbarData) }
                )
            },
            topBar = {
                if (screenTopAppBarTitle != null) {
                    CenterAlignedTopAppBar(
                        title = { Texts.TM(stringResource(resource = screenTopAppBarTitle)) },
                        navigationIcon = if (backNavigationEnable) {
                            {
                                IconButton(onClick = onBackClick) {
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
            modifier = Modifier.viewEnable(isLoadingView.not()).fillMaxSize()
        ) { innerPadding ->
            when {
                isLoadingView -> LoadingView()
                iErrorView -> ErrorView(reload = reloadScreen)
                else -> Column(
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
//        viewState: VS,
        content: @Composable ColumnScope.() -> Unit
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        ContentView(
            snackbarHostState = snackbarHostState,
            screenTopAppBarTitle = provideTopAppBarTitle(),
            backNavigationEnable = backNavigationEnable,
            topBarActions = emptyList(),
            reloadScreen = { },
            verticalScrollEnable = verticalScrollEnable,
            contentPadding = provideContentPadding(),
            onBackClick = { },
            isLoadingView = false,
            iErrorView = false,
            content = content
        )
    }

    @Immutable
    data class TopBarAction(
        val icon: ImageVector,
        val description: String,
        val onClick: () -> Unit
    )
}