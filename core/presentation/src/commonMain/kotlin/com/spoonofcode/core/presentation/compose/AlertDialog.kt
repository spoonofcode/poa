package com.spoonofcode.core.presentation.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardAlertDialog(
    title: String,
    text: String,
    confirmButtonText: String,
    dismissButtonText: String,
    confirmAction: () -> Unit,
    dismissAction: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = dismissAction,
        title = { TextTLB(title) },
        text = { TextBM(text) },
        confirmButton = {
            PrimaryButton(
                text = confirmButtonText,
                onClick = confirmAction
            )
        },
        dismissButton = {
            SecondaryButton(
                text = dismissButtonText,
                onClick = dismissAction
            )
        }
    )
}
