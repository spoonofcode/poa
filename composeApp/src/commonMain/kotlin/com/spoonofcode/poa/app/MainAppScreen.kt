package com.spoonofcode.poa.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.Navigator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.spoonofcode.core.presentation.navigation.NavigationHandler
import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.appnavigation.ProfileModuleRoute
import com.spoonofcode.feature.appnavigation.ScanModuleRoute
import com.spoonofcode.feature.auth.presentation.authModuleRouteResolver
import com.spoonofcode.feature.faq.presentation.faqModuleRouteResolver
import com.spoonofcode.feature.home.presentation.homeModuleRouteResolver
import com.spoonofcode.feature.notification.presentation.notificationModuleRouteResolver
import com.spoonofcode.feature.partner.presentation.partnerModuleRouteResolver
import com.spoonofcode.feature.product.presentation.productModuleRouteResolver
import com.spoonofcode.feature.profile.presentation.profileModuleRouteResolver
import com.spoonofcode.feature.scan.nfc.presentation.scanModuleRouteResolver
import com.spoonofcode.feature.tac.presentation.tacModuleRouteResolver
import com.spoonofcode.poa.appModuleRouteResolver
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.koin.compose.getKoin

private val navSavedStateConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeModuleRoute.Home::class)
            subclass(PartnerModuleRoute.PartnerOverview::class)
            subclass(ScanModuleRoute.Scan::class)
            subclass(ProductModuleRoute.ProductOverview::class)
            subclass(ProfileModuleRoute.ProfileDetails::class)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen() {
    val backStack = rememberNavBackStack(
        configuration = navSavedStateConfiguration,
        HomeModuleRoute.Home
    )

    val navigator = remember(backStack) { Navigator(backStack) }

    val viewModelNavigator: ViewModelNavigator by getKoin().inject()
    NavigationHandler(
        navigationFlow = viewModelNavigator.navigationEvents,
        navigator = navigator,
    )

    val currentScreen = backStack.lastOrNull() ?: HomeModuleRoute.Home
    val showBottomBar = currentScreen is HomeModuleRoute.Home
            || currentScreen is PartnerModuleRoute.PartnerOverview
            || currentScreen is ScanModuleRoute.Scan
            || currentScreen is ProductModuleRoute.ProductOverview
            || currentScreen is ProfileModuleRoute.ProfileDetails

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentScreen is HomeModuleRoute.Home,
                        onClick = {
                            backStack.clear()
                            backStack.add(HomeModuleRoute.Home)
                        },
                        label = { Text("Home") },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") }
                    )

                    NavigationBarItem(
                        selected = currentScreen is PartnerModuleRoute.PartnerOverview,
                        onClick = {
                            backStack.clear()
                            backStack.add(PartnerModuleRoute.PartnerOverview)
                        },
                        label = { Text("Home") },
                        icon = { Icon(Icons.Default.People, contentDescription = "Partner") }
                    )

                    NavigationBarItem(
                        selected = currentScreen is ScanModuleRoute.Scan,
                        onClick = {
                            backStack.clear()
                            backStack.add(ScanModuleRoute.Scan)
                        },
                        label = { Text("Scan") },
                        icon = {
                            Icon(
                                Icons.Default.QrCodeScanner,
                                contentDescription = "Scan"
                            )
                        }
                    )

                    NavigationBarItem(
                        selected = currentScreen is ProductModuleRoute.ProductOverview,
                        onClick = {
                            backStack.clear()
                            backStack.add(ProductModuleRoute.ProductOverview)
                        },
                        label = { Text("Products") },
                        icon = {
                            Icon(
                                Icons.Default.CollectionsBookmark,
                                contentDescription = "Products"
                            )
                        }
                    )

                    NavigationBarItem(
                        selected = currentScreen is ProfileModuleRoute.ProfileDetails,
                        onClick = {
                            backStack.clear()
                            backStack.add(ProfileModuleRoute.ProfileDetails)
                        },
                        label = { Text("Profile") },
                        icon = {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Profile"
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            entryProvider = entryProvider {
                appModuleRouteResolver()
                authModuleRouteResolver()
                homeModuleRouteResolver()
                partnerModuleRouteResolver()
                scanModuleRouteResolver()
                productModuleRouteResolver()
                profileModuleRouteResolver()
                tacModuleRouteResolver()
                notificationModuleRouteResolver()
                faqModuleRouteResolver()
            }
        )
    }
}