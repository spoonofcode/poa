package com.spoonofcode.feature.auth.presentation.utils

import androidx.compose.runtime.Composable

actual class GoogleAuthProvider {
    @Composable
    actual fun getUiProvider(): GoogleAuthUiProvider =
        GoogleAuthUiProvider()
}