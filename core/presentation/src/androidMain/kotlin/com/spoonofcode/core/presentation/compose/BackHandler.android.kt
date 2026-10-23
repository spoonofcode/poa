package com.spoonofcode.core.presentation.compose

import androidx.compose.runtime.Composable
import androidx.activity.compose.BackHandler as AndroidXBackHandler

@Composable
actual fun BackHandler(
    enabled: Boolean,
    onBack: () -> Unit,
) {
    AndroidXBackHandler(enabled = enabled, onBack = onBack)
}
