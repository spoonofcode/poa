package com.spoonofcode.core.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable expect fun MyWebView(
    url: String,
    modifier: Modifier = Modifier,
)
