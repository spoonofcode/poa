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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.spoonofcode.core.presentation.navigation.LocalNavViewModelStoreProvider
import com.spoonofcode.core.presentation.navigation.NavViewModelStoreProvider
import com.spoonofcode.core.presentation.navigation.NavigationHandler
import com.spoonofcode.core.presentation.navigation.ViewModelNavigator
import com.spoonofcode.feature.appnavigation.AppModuleRoute
import com.spoonofcode.feature.appnavigation.AuthModuleRoute
import com.spoonofcode.feature.appnavigation.FAQModuleRoute
import com.spoonofcode.feature.appnavigation.HomeModuleRoute
import com.spoonofcode.feature.appnavigation.NotificationModuleRoute
import com.spoonofcode.feature.appnavigation.PartnerModuleRoute
import com.spoonofcode.feature.appnavigation.ProductModuleRoute
import com.spoonofcode.feature.appnavigation.ProfileModuleRoute
import com.spoonofcode.feature.appnavigation.ScanModuleRoute
import com.spoonofcode.feature.appnavigation.TACModuleRoute
import com.spoonofcode.feature.auth.presentation.authModuleRouteResolver
import com.spoonofcode.feature.faq.presentation.faqModuleRouteResolver
import com.spoonofcode.feature.home.presentation.homeModuleRouteResolver
import com.spoonofcode.feature.notification.presentation.notificationModuleRouteResolver
import com.spoonofcode.feature.partner.presentation.partnerModuleRouteResolver
import com.spoonofcode.feature.product.presentation.productModuleRouteResolver
import com.spoonofcode.feature.profile.presentation.profileModuleRouteResolver
import com.spoonofcode.feature.scan.nfc.presentation.scanModuleRouteResolver
import com.spoonofcode.feature.tac.presentation.tacModuleRouteResolver
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.koin.compose.getKoin

private val navSavedStateConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(AppModuleRoute.MainHost::class)
            subclass(AuthModuleRoute.Login::class)
            subclass(AuthModuleRoute.LoginCode::class)
            subclass(FAQModuleRoute.FAQ::class)
            subclass(HomeModuleRoute.Home::class)
            subclass(NotificationModuleRoute.NotificationDetails::class)
            subclass(NotificationModuleRoute.NotificationOverview::class)
            subclass(NotificationModuleRoute.NotificationSettings::class)
            subclass(NotificationModuleRoute.NotificationChannel::class)
            subclass(PartnerModuleRoute.PartnerDetails::class)
            subclass(PartnerModuleRoute.PartnerOverview::class)
            subclass(PartnerModuleRoute.PartnerEdit::class)
            subclass(PartnerModuleRoute.CustomerOverview::class)
            subclass(PartnerModuleRoute.PartnerPanel::class)
            subclass(PartnerModuleRoute.PartnerNotifications::class)
            subclass(PartnerModuleRoute.PartnerNotificationDetails::class)
            subclass(PartnerModuleRoute.PartnerNotificationEdit::class)
            subclass(PartnerModuleRoute.PartnerNotificationType::class)
            subclass(ProductModuleRoute.ProductDetails::class)
            subclass(ProductModuleRoute.ProductEdit::class)
            subclass(ProductModuleRoute.ProductOverview::class)
            subclass(ProductModuleRoute.ProductSeriesOverview::class)
            subclass(ProfileModuleRoute.ProfileDetails::class)
            subclass(ProfileModuleRoute.ProfileEdit::class)
            subclass(ScanModuleRoute.Scan::class)
            subclass(TACModuleRoute.TAC::class)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(
    initialRoutes: List<NavKey>
) {
    val backStack = rememberNavBackStack(
        configuration = navSavedStateConfiguration,
        *initialRoutes.toTypedArray()
    )

    val viewModelStoreProvider = remember { NavViewModelStoreProvider() }
    val currentKeys = backStack.toSet()
    LaunchedEffect(currentKeys) {
        viewModelStoreProvider.cleanUp(currentKeys)
    }

    val viewModelNavigator: ViewModelNavigator by getKoin().inject()
    NavigationHandler(
        navigationFlow = viewModelNavigator.navigationEvents,
        backStack = backStack,
    )

    val currentScreen = backStack.lastOrNull() ?: initialRoutes.last()
    val showBottomBar = currentScreen is HomeModuleRoute.Home
            || currentScreen is PartnerModuleRoute.PartnerOverview
            || currentScreen is ScanModuleRoute.Scan
            || currentScreen is ProductModuleRoute.ProductOverview
            || currentScreen is ProfileModuleRoute.ProfileDetails

    CompositionLocalProvider(LocalNavViewModelStoreProvider provides viewModelStoreProvider) {
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
                            label = { Text("Partner") },
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
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                ),
                entryProvider = entryProvider {
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
}
