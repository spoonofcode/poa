package com.spoonofcode.poa.feature.product.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.spoonofcode.poa.feature.product.presentation.overview.ProductOverviewScreen
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.products
import org.jetbrains.compose.resources.stringResource

object ProductOverviewTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(resource = Res.string.products)
            val icon = rememberVectorPainter(Icons.Default.CollectionsBookmark)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        ProductOverviewScreen().Content()
    }
}