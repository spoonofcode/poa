package com.spoonofcode.poa.core.ui.compose.snackbar.ext

import androidx.compose.material3.SnackbarHostState
import com.spoonofcode.poa.core.ui.compose.snackbar.SnackbarEvent
import com.spoonofcode.poa.core.ui.compose.snackbar.CustomSnackbarVisuals

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