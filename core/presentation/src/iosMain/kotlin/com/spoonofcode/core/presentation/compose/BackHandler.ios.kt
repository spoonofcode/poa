package com.spoonofcode.core.presentation.compose

import androidx.compose.runtime.Composable

@Composable
actual fun BackHandler(
    enabled: Boolean,
    onBack: () -> Unit,
) {
    // Platform specific back gesture on iOS
}
