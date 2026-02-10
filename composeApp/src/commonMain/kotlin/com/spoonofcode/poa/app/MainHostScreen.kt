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
import com.spoonofcode.core.presentation.compose.Paddings.bottomScreenPadding
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.feature.home.presentation.HomeTab
import com.spoonofcode.feature.partner.presentation.PartnerOverviewTab
import com.spoonofcode.feature.product.presentation.ProductOverviewTab
import com.spoonofcode.feature.profile.presentation.details.ProfileTab
import com.spoonofcode.feature.scan.presentation.ScanTab

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