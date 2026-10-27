package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.spoonofcode.core.data.ext.formatedLocalDate
import com.spoonofcode.core.data.ext.isAfterToday
import com.spoonofcode.core.data.ext.toZonedLocalDate
import com.spoonofcode.core.data.ext.zonedEndOfDayInMillis
import com.spoonofcode.core.data.ext.zonedStartOfDayInMillis
import com.spoonofcode.core.data.utils.LocalDateUtils
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.accept
import com.spoonofcode.core.presentation.cancel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Instant

@Composable
fun StandardDatePicker(
    value: LocalDate = LocalDateUtils.today(),
    onValueChange: (LocalDate) -> Unit,
    label: String? = null,
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    isRequired: Boolean = false,
) {
    val date = remember { mutableStateOf(value) }
    val isOpen = remember { mutableStateOf(false) }

    Box(
        modifier = modifier,
    ) {
        OutlinedTextFieldStandard(
            value = date.value.formatedLocalDate(),
            onValueChange = {},
            readOnly = true,
            label = label,
            isRequired = isRequired,
            trailingIcon = {
                IconButton(
                    onClick = {},
                ) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Calendar")
                }
            },
        )

        // Transparent overlay to capture clicks
        Box(
            modifier =
                Modifier
                    .matchParentSize()
                    .clickable(onClick = { isOpen.value = true }),
        )
    }

    if (isOpen.value) {
        StandardDatePickerDialog(
            onAccept = {
                isOpen.value = false

                if (it != null) {
                    date.value =
                        Instant
                            .fromEpochMilliseconds(it)
                            .toZonedLocalDate()
                }
                onValueChange(date.value)
            },
            onCancel = {
                isOpen.value = false
            },
            selectedDate = date.value.zonedStartOfDayInMillis(),
            minDateUtcMillis = minDate?.zonedStartOfDayInMillis(),
            maxDateUtcMillis = maxDate?.zonedEndOfDayInMillis(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardDatePickerWithTimer(
    value: LocalDateTime,
    onValueChange: (LocalDateTime) -> Unit,
    label: String? = null,
    minDateTime: LocalDateTime? = null,
    maxDateTime: LocalDateTime? = null,
    isRequired: Boolean = false,
) {
    Row {
        StandardDatePicker(
            value = value.date,
            onValueChange = {
                onValueChange(
                    LocalDateTime(
                        date = it,
                        time = value.time,
                    ),
                )
            },
            label = label,
            minDate = minDateTime?.date,
            maxDate = maxDateTime?.date,
            modifier = Modifier.weight(1f),
            isRequired = isRequired,
        )
        SpacerHorizontalBetweenFields()
        StandardTimePicker(
            label = "",
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            onConfirm = {},
            onDismiss = {},
            modifier = Modifier.weight(.5f),
            minTime = if (value.isAfterToday()) null else minDateTime?.time,
        )
    }
}

@Composable
private fun StandardDatePickerDialog(
    onAccept: (Long?) -> Unit,
    onCancel: () -> Unit,
    selectedDate: Long,
    minDateUtcMillis: Long? = null,
    maxDateUtcMillis: Long? = null,
) {
    val state =
        rememberDatePickerState(
            initialSelectedDateMillis = selectedDate,
            selectableDates =
                if (minDateUtcMillis == null && maxDateUtcMillis == null) {
                    DatePickerDefaults.AllDates
                } else {
                    object : SelectableDates {
                        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                            val afterMin = minDateUtcMillis == null || utcTimeMillis >= minDateUtcMillis
                            val beforeMax = maxDateUtcMillis == null || utcTimeMillis <= maxDateUtcMillis
                            return afterMin && beforeMax
                        }
                    }
                },
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
        },
    ) {
        DatePicker(state = state)
    }
}
