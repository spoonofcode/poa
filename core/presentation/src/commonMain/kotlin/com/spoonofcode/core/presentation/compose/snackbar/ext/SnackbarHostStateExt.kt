package com.spoonofcode.core.presentation.compose.snackbar.ext

import androidx.compose.material3.SnackbarHostState
import com.spoonofcode.core.presentation.compose.snackbar.CustomSnackbarVisuals
import com.spoonofcode.core.presentation.compose.snackbar.SnackbarEvent
import com.spoonofcode.core.presentation.helpers.asStringAsync

suspend fun SnackbarHostState.showSnackbar(snackbarEvent: SnackbarEvent) {
    showSnackbar(
        CustomSnackbarVisuals(
            message = snackbarEvent.message.asStringAsync(),
            actionLabel = snackbarEvent.actionLabel?.asStringAsync(),
            duration = snackbarEvent.duration,
            type = snackbarEvent.type,
        ),
    )
}
