package com.spoonofcode.core.presentation.compose.snackbar.ext

import androidx.compose.material3.SnackbarHostState
import com.spoonofcode.core.presentation.compose.snackbar.CustomSnackbarVisuals
import com.spoonofcode.core.presentation.compose.snackbar.SnackbarEvent

suspend fun SnackbarHostState.showSnackbar(
    snackbarEvent: SnackbarEvent,
) {
    showSnackbar(
        CustomSnackbarVisuals(
            message = snackbarEvent.message,
            actionLabel = snackbarEvent.actionLabel,
            duration = snackbarEvent.duration,
            type = snackbarEvent.type,
        )
    )
}