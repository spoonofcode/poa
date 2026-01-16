package com.spoonofcode.poa.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.core.ui.theme.AppTheme
import com.spoonofcode.poa.feature.login.presentation.login.LoginScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin

@Composable
@Preview
fun App(notificationId: String?) {
    val sessionManager: SessionManager = getKoin().get()

    AppTheme {
        Navigator(
            screens = getStartScreens(
                sessionInitialized = sessionManager.isSessionInitialized(),
                notificationId = notificationId
            )
        ) { navigator ->
            SlideTransition(navigator = navigator)
        }
    }
}

@Composable
private fun getStartScreens(sessionInitialized: Boolean, notificationId: String?): List<Screen> =
    if (sessionInitialized) {
        if (notificationId.isNullOrEmpty()) {
            listOf(MainHostScreen())
        } else {
            listOf(
                MainHostScreen(),
                com.spoonofcode.poa.feature.notification.presentation.overview.NotificationOverviewScreen(),
                com.spoonofcode.poa.feature.notification.presentation.details.NotificationDetailsScreen(
                    notificationId = notificationId
                )
            )
        }
    } else {
        listOf(LoginScreen())
    }
