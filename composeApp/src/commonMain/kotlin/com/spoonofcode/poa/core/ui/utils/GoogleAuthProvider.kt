package com.spoonofcode.poa.core.ui.utils

import androidx.compose.runtime.Composable

expect class GoogleAuthProvider {
    @Composable
    fun getUiProvider(): GoogleAuthUiProvider
}