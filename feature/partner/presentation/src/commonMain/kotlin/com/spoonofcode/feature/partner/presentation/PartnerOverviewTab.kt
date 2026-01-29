package com.spoonofcode.feature.partner.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.spoonofcode.feature.partner.presentation.overview.PartnerOverviewScreen
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.partners
import org.jetbrains.compose.resources.stringResource

object PartnerOverviewTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(resource = Res.string.partners)
            val icon = rememberVectorPainter(Icons.Default.People)

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
        PartnerOverviewScreen().Content()
    }
}