package com.spoonofcode.poa.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation3.runtime.NavKey
import com.spoonofcode.core.presentation.compose.LoadingView
import com.spoonofcode.core.presentation.theme.AppTheme
import com.spoonofcode.feature.appnavigation.AuthModuleRoute
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    notificationId: String?,
    viewModel: AppViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    AppTheme {
        when (val currentState = state) {
            is AppViewState.Loading -> {
                LoadingView()
            }

            is AppViewState.LoggedIn, is AppViewState.LoggedOut -> {
                MainAppScreen(
                    initialRoutes = getStartRoutes(
                        sessionInitialized = currentState is AppViewState.LoggedIn,
                        notificationId = notificationId
                    )
                )
            }
        }
    }
}

private fun getStartRoutes(
    sessionInitialized: Boolean,
    notificationId: String?
): List<NavKey> =
    if (sessionInitialized) {
        if (notificationId.isNullOrEmpty()) {
            listOf(HomeModuleRoute.Home)
        } else {
            listOf(
                HomeModuleRoute.Home,
                NotificationModuleRoute.NotificationOverview,
                NotificationModuleRoute.NotificationDetails(
                    notificationId = notificationId
                ),
            )
        }
    } else {
        listOf(AuthModuleRoute.Login)
    }
