package com.spoonofcode.core.presentation.utils

import androidx.compose.runtime.Composable

actual class GoogleAuthProvider {
    @Composable
    actual fun getUiProvider(): GoogleAuthUiProvider =
        GoogleAuthUiProvider()
}