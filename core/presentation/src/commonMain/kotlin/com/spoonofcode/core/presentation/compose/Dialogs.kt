package com.spoonofcode.core.presentation.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

object Dialogs {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AlertDialog(
        title: String,
        text: String,
        confirmButtonText: String,
        dismissButtonText: String,
        confirmAction: () -> Unit,
        dismissAction: () -> Unit,
    ) {
        AlertDialog(
            onDismissRequest = dismissAction,
            title = { Texts.TLB(title) },
            text = { Texts.BM(text) },
            confirmButton = {
                Buttons.PrimaryButton(
                    text = confirmButtonText,
                    onClick = confirmAction
                )
            },
            dismissButton = {
                Buttons.SecondaryButton(
                    text = dismissButtonText,
                    onClick = dismissAction
                )
            }
        )
    }
}