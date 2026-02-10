package com.spoonofcode.core.presentation.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics

fun Modifier.viewEnable(enable: Boolean) =
    if (enable) {
        this
    } else {
        pointerInput(Unit) {
            awaitPointerEventScope {
                // we should wait for all new pointer events
                while (true) {
                    awaitPointerEvent(pass = PointerEventPass.Initial)
                        .changes
                        .forEach(PointerInputChange::consume)
                }
            }
        }
    }

fun Modifier.addIf(
    condition: Boolean,
    action: @Composable Modifier.() -> (Modifier),
): Modifier = composed {
    if (condition) {
        action.invoke(this)
    } else {
        this
    }
}

fun Modifier.onClickSemantics(onClick: (() -> Unit)?) =
    if (onClick != null) {
        semantics {
            onClick {
                onClick()
                true
            }
        }
    } else {
        this
    }