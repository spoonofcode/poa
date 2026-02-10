package com.spoonofcode.core.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

expect @Composable fun MyWebView(
    url: String,
    modifier: Modifier = Modifier
)