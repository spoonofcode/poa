package com.spoonofcode.feature.scan.nfc.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.stringResource

object ScanTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(resource = Res.string.scan)
            val icon = rememberVectorPainter(Icons.Default.QrCodeScanner)

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
        ScanScreen().Content()
    }
}