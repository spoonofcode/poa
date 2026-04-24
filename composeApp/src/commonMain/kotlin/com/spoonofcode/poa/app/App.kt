package com.spoonofcode.poa.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.spoonofcode.core.presentation.compose.LoadingView
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolver
import com.spoonofcode.core.presentation.theme.AppTheme
import com.spoonofcode.feature.appnavigation.AuthModule
import com.spoonofcode.feature.appnavigation.NotificationModule
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    notificationId: String?,
    viewModel: AppViewModel = koinViewModel()
) {
    val routeResolver: VoyagerRouteResolver = getKoin().get()

    val state by viewModel.state.collectAsState()

    AppTheme {
        when (val currentState = state) {
            is AppViewState.Loading -> {
                LoadingView()
            }

            is AppViewState.LoggedIn, is AppViewState.LoggedOut -> {
                Navigator(
                    screens = getStartScreens(
                        sessionInitialized = currentState is AppViewState.LoggedIn,
                        notificationId = notificationId,
                        routeResolver = routeResolver
                    )
                ) { navigator ->
                    SlideTransition(navigator = navigator)
                }
            }
        }
    }
}

@Composable
private fun getStartScreens(
    sessionInitialized: Boolean,
    notificationId: String?,
    routeResolver: VoyagerRouteResolver
): List<Screen> =
    if (sessionInitialized) {
        if (notificationId.isNullOrEmpty()) {
            listOf(MainHostScreen())
        } else {
            listOf(
                MainHostScreen(),
                routeResolver.resolve(NotificationModule.NotificationOverviewScreen),
                routeResolver.resolve(
                    NotificationModule.NotificationDetailsScreen(
                        notificationId = notificationId
                    )
                ),
            )
        }
    } else {
        listOf(routeResolver.resolve(AuthModule.LoginScreen))
    }
