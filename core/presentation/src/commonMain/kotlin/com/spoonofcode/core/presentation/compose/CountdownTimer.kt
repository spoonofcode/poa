package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.hours
import com.spoonofcode.core.presentation.minutes
import com.spoonofcode.core.presentation.seconds
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun CountdownTimer(targetTimeMillis: Long) {
    var remainingTime by remember { mutableStateOf(0L) }

    LaunchedEffect(targetTimeMillis) {
        while (true) {
            val currentTime = Clock.System.now().toEpochMilliseconds()
            val newRemainingTime = (targetTimeMillis - currentTime).coerceAtLeast(0)

            remainingTime = newRemainingTime

            if (newRemainingTime <= 0) {
                break
            }

            delay(1000L)
        }
    }

    val totalSeconds = remainingTime / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        if (remainingTime > 0) {
            CountdownUnit(value = hours, label = stringResource(Res.string.hours))
            CountdownUnit(value = minutes, label = stringResource(Res.string.minutes))
            CountdownUnit(value = seconds, label = stringResource(Res.string.seconds))
        } else {
            CountdownUnit(value = 0, label = stringResource(Res.string.hours))
            CountdownUnit(value = 0, label = stringResource(Res.string.minutes))
            CountdownUnit(value = 0, label = stringResource(Res.string.seconds))
        }
    }
}

@Composable
private fun CountdownUnit(value: Long, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(size = 8.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = value.toString().padStart(2, '0'),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(text = label, fontSize = 10.sp, color = MaterialTheme.colorScheme.onPrimary)
    }
}