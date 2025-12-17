package com.spoonofcode.poa.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.spoonofcode.poa.core.ui.Paddings.bottomScreenPadding
import com.spoonofcode.poa.core.ui.compose.Texts
import com.spoonofcode.poa.feature.home.HomeTab
import com.spoonofcode.poa.feature.partner.presentation.PartnerOverviewTab
import com.spoonofcode.poa.feature.product.presentation.ProductOverviewTab
import com.spoonofcode.poa.feature.profile.details.ProfileTab
import com.spoonofcode.poa.feature.scan.ScanTab

class MainHostScreen() : Screen {

    @Composable
    override fun Content() {
        TabNavigator(
            tab = HomeTab
        ) {
            Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.background,
                ) {
                    TabNavigationItem(HomeTab)
                    TabNavigationItem(PartnerOverviewTab)
                    TabNavigationItem(ScanTab)
                    TabNavigationItem(ProductOverviewTab)
                    TabNavigationItem(ProfileTab)
                }
            }, content = {
                Box(
                    modifier = Modifier.padding(bottom = bottomScreenPadding).fillMaxSize()
                ) {
                    CurrentTab()
                }
            })
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator: TabNavigator = LocalTabNavigator.current

        NavigationBarItem(
            selected = tabNavigator.current == tab,
            onClick = { tabNavigator.current = tab },
            icon = {
                tab.options.icon?.let { icon ->
                    Icon(
                        painter = icon, contentDescription = tab.options.title
                    )
                }
            },
            label = {
                Texts.LS(text = tab.options.title)
            }
        )
    }
}