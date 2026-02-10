package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.spoonofcode.core.data.ext.formatedLocalDate
import com.spoonofcode.core.data.utils.LocalDateTimeUtils
import com.spoonofcode.core.data.utils.TimeZoneUtils
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.accept
import com.spoonofcode.core.presentation.cancel
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

object DatePickers {

    @OptIn(ExperimentalTime::class)
    @Composable
    fun DatePicker(
        value: LocalDate = LocalDateTimeUtils.now().date,
        onValueChange: (LocalDate) -> Unit,
        modifier: Modifier = Modifier.fillMaxWidth(),
        label: String? = null,
    ) {
        val date = remember { mutableStateOf(value) }
        val isOpen = remember { mutableStateOf(false) }

        Box(
            modifier = modifier
        ) {
            TextFields.Outlined(
                value = date.value.formatedLocalDate(),
                onValueChange = {},
                readOnly = true,
                label = label,
                trailingIcon = {
                    IconButton(
                        onClick = {}
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
            DatePickerDialog(
                onAccept = {
                    isOpen.value = false

                    if (it != null) {
                        date.value = Instant
                            .fromEpochMilliseconds(it)
                            .toLocalDateTime(TimeZoneUtils.DEFAULT_ZONE).date
                    }
                    onValueChange(date.value)
                },
                onCancel = {
                    isOpen.value = false
                },
                selectedDate = date.value
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DatePickerWithTimer(
        value: LocalDateTime,
        label: String? = null,
        onValueChange: (LocalDateTime) -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier,
        ) {
            label?.let {
                Texts.BLB(text = it)
            }
            Row {
                DatePicker(
                    value = value.date,
                    onValueChange = {
                        onValueChange(
                            LocalDateTime(
                                date = it,
                                time = value.time
                            )
                        )
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacers.HorizontalBetweenFields()
                TimePickers.TimePicker(
                    value = value,
                    onValueChange = {
                        onValueChange(it)
                    },
                    onConfirm = {},
                    onDismiss = {},
                    modifier = Modifier.weight(.5f)
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
    @Composable
    private fun DatePickerDialog(
        onAccept: (Long?) -> Unit,
        onCancel: () -> Unit,
        selectedDate: LocalDate,
    ) {
        // We need to add on day to have selectedDate in Milliseconds
        val state = rememberDatePickerState(
            initialSelectedDateMillis = selectedDate
                .atStartOfDayIn(TimeZoneUtils.DEFAULT_ZONE)
                .plus(1.days)
                .toEpochMilliseconds()
        )

        DatePickerDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = { onAccept(state.selectedDateMillis) }) {
                    Text(stringResource(resource = Res.string.accept))
                }
            },
            dismissButton = {
                Button(onClick = onCancel) {
                    Text(stringResource(resource = Res.string.cancel))
                }
            }
        ) {
            DatePicker(state = state)
        }
    }

}