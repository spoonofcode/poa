package com.spoonofcode.poa.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.spoonofcode.core.data.network.SessionManager
import com.spoonofcode.core.presentation.navigation.route.VoyagerRouteResolver
import com.spoonofcode.core.presentation.theme.AppTheme
import com.spoonofcode.feature.appnavigation.AuthModule
import com.spoonofcode.feature.appnavigation.NotificationModule
import org.koin.compose.getKoin

@Composable
@Preview
fun App(notificationId: String?) {
    val sessionManager: SessionManager = getKoin().get()
    val routeResolver: VoyagerRouteResolver = getKoin().get()

    AppTheme {
        Navigator(
            screens = getStartScreens(
                sessionInitialized = sessionManager.isSessionInitialized(),
                notificationId = notificationId,
                routeResolver
            )
        ) { navigator ->
            SlideTransition(navigator = navigator)
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
