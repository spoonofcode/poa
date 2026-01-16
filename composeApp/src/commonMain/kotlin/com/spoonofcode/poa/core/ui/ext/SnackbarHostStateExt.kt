package com.spoonofcode.poa.core.ui.ext

import androidx.compose.material3.SnackbarHostState
import com.spoonofcode.poa.core.ui.snackbar.SnackbarEvent
import com.spoonofcode.poa.core.ui.compose.CustomSnackbarVisuals

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