package com.spoonofcode.core.presentation.base

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

        viewModel.sessionState.collectAsStateWithLifecycle()
        viewModel.deviceRegistrationState.collectAsStateWithLifecycle()
// TODO Fix networkState
//    viewModel.networkState.collectAsStateWithLifecycle()

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

    @Composable
    private fun HandleSessionEvents(onExtendSession: () -> Unit, onLogoutNow: () -> Unit) {
        val sessionInactivityManager = koinInject<SessionInactivityManager>()
        val sessionWarning by sessionInactivityManager.warning.collectAsStateWithLifecycle()
        when (sessionWarning) {
            SessionInactivityManager.Warning.ExpiringSoon -> {
                Dialogs.AlertDialog(
                    title = stringResource(R.string.session_inactivity_dialog_title),
                    text = {
                        Texts.BM(stringResource(R.string.session_expiring_soon_message))
                    },
                    confirmButtonText = stringResource(R.string.session_expiring_soon_continue),
                    dismissButtonText = stringResource(R.string.session_expiring_soon_logout),
                    confirmAction = onExtendSession,
                    dismissAction = onLogoutNow,
                )
            }

            SessionInactivityManager.Warning.Expired -> {
                Dialogs.AlertDialog(
                    title = stringResource(R.string.session_inactivity_dialog_title),
                    text = {
                        Texts.BM(stringResource(R.string.session_expired_message))
                    },
                    confirmButtonText = stringResource(R.string.session_expired_ok),
                    confirmAction = onLogoutNow,
                )
            }

            SessionInactivityManager.Warning.None,
            SessionInactivityManager.Warning.Acknowledged -> Unit
        }
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