package com.spoonofcode.poa.app

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformBackHandlerSupport(content: @Composable () -> Unit) {
    content()
}
