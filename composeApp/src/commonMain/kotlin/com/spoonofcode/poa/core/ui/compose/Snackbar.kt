package com.spoonofcode.poa.core.ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.spoonofcode.poa.core.ui.Paddings
import com.spoonofcode.poa.core.ui.SnackbarEvent
import com.spoonofcode.poa.core.ui.SnackbarType
import com.spoonofcode.poa.core.ui.ext.showSnackbar
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
            modifier = Modifier.padding(Paddings.screenPadding),
            containerColor = getContainerColor(visuals.type),
            contentColor = getContentColor(visuals.type),
            action = {
                visuals.actionLabel?.let { actionLabel ->
                    TextButton(onClick = { snackbarData.performAction() }) {
                        Text(text = actionLabel)
                    }
                }
            }
        ) {
            Text(text = visuals.message)
        }
    } else {
        // Fallback: standard Snackbar
        Snackbar {
            Text(text = visuals.message)
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
    SnackbarType.INFO -> MaterialTheme.colorScheme.onPrimaryContainer
    SnackbarType.ERROR -> MaterialTheme.colorScheme.onErrorContainer
    SnackbarType.SUCCESS -> MaterialTheme.colorScheme.onSecondaryContainer
}

@Composable
private fun getContentColor(type: SnackbarType): Color = when (type) {
    SnackbarType.INFO -> MaterialTheme.colorScheme.onPrimary
    SnackbarType.ERROR -> MaterialTheme.colorScheme.onError
    SnackbarType.SUCCESS -> MaterialTheme.colorScheme.onSecondary
}