package com.spoonofcode.core.presentation.compose.snackbar

import androidx.compose.material3.SnackbarDuration

sealed class SnackbarEvent(
    val message: String,
    val actionLabel: String? = null,
    val duration: SnackbarDuration,
    val type: SnackbarType,
) {
    data object Offline : SnackbarEvent(
        message = "No internet connection",
        duration = SnackbarDuration.Indefinite,
        type = SnackbarType.ERROR
    )

    data object Online : SnackbarEvent(
        message = "Internet connection restored",
        duration = SnackbarDuration.Short,
        type = SnackbarType.INFO
    )

    class Error(
        message: String
    ) : SnackbarEvent(
        message = message,
        actionLabel = "OK",
        duration = SnackbarDuration.Indefinite,
        type = SnackbarType.ERROR
    )
    class Success(
        message: String
    ) : SnackbarEvent(
        message = message,
        actionLabel = "OK",
        duration = SnackbarDuration.Short,
        type = SnackbarType.SUCCESS
    )
}

enum class SnackbarType {
    INFO,
    ERROR,
    SUCCESS,
}