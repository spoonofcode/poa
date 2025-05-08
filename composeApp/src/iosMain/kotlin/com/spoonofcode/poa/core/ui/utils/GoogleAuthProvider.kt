package com.spoonofcode.poa.core.ui.utils

import androidx.compose.runtime.Composable

actual class GoogleAuthProvider {
    @Composable
    actual fun getUiProvider(): GoogleAuthUiProvider =
        GoogleAuthUiProvider()
}