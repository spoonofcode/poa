package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.spoonofcode.core.data.ext.formatedTime
import com.spoonofcode.core.data.utils.LocalDateTimeUtils
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

object TimePickers {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TimePicker(
        value: LocalDateTime = LocalDateTimeUtils.now(),
        onValueChange: (LocalDateTime) -> Unit,
        label: String? = null,
        minTime: LocalTime? = null,
        onConfirm: (TimePickerState) -> Unit,
        onDismiss: () -> Unit,
        modifier: Modifier = Modifier.fillMaxWidth(),
    ) {
        val timePickerState = rememberTimePickerState(
            initialHour = value.hour,
            initialMinute = value.minute,
            is24Hour = true,
        )
        val isOpen = remember { mutableStateOf(false) }

        Box(
            modifier = modifier
        ) {
            TextFields.Outlined(
                value = value.formatedTime(),
                readOnly = true,
                label = label,
                onValueChange = {},
                trailingIcon = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "Calendar")
                    }
                }
            )

            // Transparent overlay to capture clicks
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable(onClick = { isOpen.value = true })
            )
        }

        if (isOpen.value) {
            TimePickerDialog(
                onDismiss = {
                    isOpen.value = false // close dialog
                    onDismiss()
                },
                onConfirm = {
                    onValueChange(
                        LocalDateTime(
                            value.date,
                            LocalTime(
                                hour = timePickerState.hour,
                                minute = timePickerState.minute,
                                second = 0,
                                nanosecond = 0
                            )
                        )
                    )

                    isOpen.value = false //close dialog
                    onConfirm(timePickerState)
                }
            ) {
                if (minTime != null) {
                    MinTimePicker(
                        state = timePickerState,
                        minHour = minTime.hour,
                        minMinute = minTime.minute
                    )
                } else {
                    TimePicker(
                        state = timePickerState,
                    )
                }
            }
        }
    }

    @Composable
    private fun TimePickerDialog(
        onDismiss: () -> Unit,
        onConfirm: () -> Unit,
        content: @Composable () -> Unit
    ) {
        AlertDialog(
            onDismissRequest = onDismiss,
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Dismiss")
                }
            },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text("OK")
                }
            },
            text = { content() }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MinTimePicker(
        state: TimePickerState,
        minHour: Int,
        minMinute: Int,
    ) {
        val minTotal = minHour * 60 + minMinute

        LaunchedEffect(state.hour, state.minute) {
            val pickedTotal = state.hour * 60 + state.minute
            if (pickedTotal < minTotal) {
                state.hour = minHour
                state.minute = minMinute
            }
        }

        TimePicker(state = state)
    }
}