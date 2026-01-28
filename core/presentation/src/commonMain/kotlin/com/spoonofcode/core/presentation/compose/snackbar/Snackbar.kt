package com.spoonofcode.core.presentation.compose.snackbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.spoonofcode.core.presentation.compose.Paddings
import com.spoonofcode.core.presentation.compose.Texts
import com.spoonofcode.core.presentation.compose.snackbar.ext.showSnackbar
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

data class CustomSnackbarVisuals(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    val type: SnackbarType,
) : SnackbarVisuals

@Composable
fun Snackbar(
    snackbarData: SnackbarData
) {
    val visuals = snackbarData.visuals
    if (visuals is CustomSnackbarVisuals) {
        // Our custom visuals
        Snackbar(
            modifier = Modifier.Companion.padding(Paddings.screenPadding),
            containerColor = getContainerColor(visuals.type),
            contentColor = getContentColor(visuals.type),
            action = {
                visuals.actionLabel?.let { actionLabel ->
                    TextButton(onClick = { snackbarData.performAction() }) {
                        Texts.BM(text = actionLabel)
                    }
                }
            }
        ) {
            Texts.BM(text = visuals.message)
        }
    } else {
        // Fallback: standard Snackbar
        Snackbar {
            Texts.BM(text = visuals.message)
        }
    }
}

@Composable
fun setSnackbarHostState(
    snackbarHostState: SnackbarHostState,
    snackbarEvent: SharedFlow<SnackbarEvent>
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(snackbarEvent) {
        snackbarEvent.collect { snackbarEvent ->
            coroutineScope.launch {
                snackbarHostState.currentSnackbarData?.dismiss()
                snackbarHostState.showSnackbar(
                    snackbarEvent
                )
            }
        }
    }
}

@Composable
private fun getContainerColor(type: SnackbarType): Color = when (type) {
    SnackbarType.INFO -> MaterialTheme.colorScheme.surfaceVariant
    SnackbarType.ERROR -> MaterialTheme.colorScheme.onErrorContainer
    SnackbarType.SUCCESS -> MaterialTheme.colorScheme.secondaryContainer
}

@Composable
private fun getContentColor(type: SnackbarType): Color = when (type) {
    SnackbarType.INFO -> MaterialTheme.colorScheme.onSurfaceVariant
    SnackbarType.ERROR -> MaterialTheme.colorScheme.onError
    SnackbarType.SUCCESS -> MaterialTheme.colorScheme.onSecondaryContainer
}