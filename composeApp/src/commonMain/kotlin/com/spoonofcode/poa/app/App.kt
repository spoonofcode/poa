package com.spoonofcode.poa.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.spoonofcode.poa.core.network.SessionManager
import com.spoonofcode.poa.core.ui.theme.AppTheme
import com.spoonofcode.poa.feature.login.login.LoginScreen
import com.spoonofcode.poa.feature.partner.details.PartnerDetailsScreen
import com.spoonofcode.poa.feature.product.details.ProductDetailsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin

@Composable
@Preview
fun App() {
    val sessionManager: SessionManager = getKoin().get()

    AppTheme {
        Navigator(getStartScreen(sessionManager.isSessionInitialized())) { navigator ->
            SlideTransition(navigator = navigator)
        }
    }
}

@Composable
private fun getStartScreen(sessionInitialized: Boolean): Screen {
//    return DemoScreen()
//    return MainHostScreen()

//    return if (sessionInitialized) {
//        MainHostScreen()
//    } else {
//        LoginScreen()
//    }

//    return LoginScreen()
    return PartnerDetailsScreen(partnerId = 1)

}
