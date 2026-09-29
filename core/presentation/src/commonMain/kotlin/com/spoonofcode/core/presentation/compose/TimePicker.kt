package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spoonofcode.core.data.ext.formatedTime
import com.spoonofcode.core.data.utils.LocalDateTimeUtils
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.confirm
import com.spoonofcode.core.presentation.dismiss
import com.spoonofcode.core.presentation.minimum_time
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTimePicker(
    value: LocalDateTime = LocalDateTimeUtils.now(),
    onValueChange: (LocalDateTime) -> Unit,
    label: String? = null,
    minTime: LocalTime? = null,
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
) {
    val isOpen = remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        OutlinedTextFieldStandard(
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

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable(onClick = { isOpen.value = true })
        )
    }

    if (isOpen.value) {
        val initialTime = remember(value.time, minTime) {
            if (minTime != null && value.time < minTime) minTime else value.time
        }

        key(isOpen.value) {
            val timePickerState = rememberTimePickerState(
                initialHour = initialTime.hour,
                initialMinute = initialTime.minute,
                is24Hour = true,
            )

            val isTimeValid by remember(minTime) {
                derivedStateOf {
                    val selectedTotalMinutes = timePickerState.hour * 60 + timePickerState.minute
                    val minTotalMinutes = minTime?.let { it.hour * 60 + it.minute }
                    minTotalMinutes == null || selectedTotalMinutes >= minTotalMinutes
                }
            }

            InternalTimePickerDialog(
                confirmEnabled = isTimeValid,
                onDismiss = {
                    isOpen.value = false
                    onDismiss()
                },
                onConfirm = {
                    if (!isTimeValid) return@InternalTimePickerDialog

                    val selectedTime = LocalTime(
                        hour = timePickerState.hour,
                        minute = timePickerState.minute,
                        second = 0,
                        nanosecond = 0
                    )

                    onValueChange(
                        LocalDateTime(
                            value.date,
                            selectedTime
                        )
                    )

                    isOpen.value = false
                    onConfirm(timePickerState)
                }
            ) {
                Column {
                    TimePicker(
                        state = timePickerState,
                    )
                    if (!isTimeValid && minTime != null) {
                        Text(
                            text = stringResource(Res.string.minimum_time, minTime.formatedTime()),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InternalTimePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    confirmEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(stringResource(Res.string.dismiss))
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() },
                enabled = confirmEnabled
            ) {
                Text(stringResource(Res.string.confirm))
            }
        },
        text = { content() }
    )
}
