package com.spoonofcode.core.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.jetbrains.compose.resources.stringResource
import com.spoonofcode.core.presentation.Res
import com.spoonofcode.core.presentation.time_is_up
import com.spoonofcode.core.presentation.time_remaining
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun Countdown(expirationDate: LocalDateTime) {
    CountdownUntil(expirationDate) { formatted, _, finished ->
        if (finished) {
            Texts.BLB(text = stringResource(Res.string.time_is_up))
        } else {
            Column {
                Texts.BLB(text = stringResource(Res.string.time_remaining))
                Texts.BLB(text = formatted)
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
private fun CountdownUntil(
    expirationDate: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
    tickMillis: Long = 1_000L,
    content: @Composable (formatted: String, remaining: Duration, isFinished: Boolean) -> Unit
) {
    // Convert once
    val targetInstant = remember(expirationDate, timeZone) { expirationDate.toInstant(timeZone) }

    val remaining by produceState(
        initialValue = (targetInstant - Clock.System.now()).coerceAtLeast(ZERO),
        key1 = targetInstant,
        key2 = tickMillis
    ) {
        while (true) {
            val now = Clock.System.now()
            value = (targetInstant - now).coerceAtLeast(ZERO)
            if (value == ZERO) break
            delay(tickMillis)
        }
    }

    content(remaining.formatDHMS(), remaining, remaining == ZERO)
}

private fun Duration.formatDHMS(): String {
    val total = inWholeSeconds
    val days = total / 86_400
    val hours = (total % 86_400) / 3_600
    val minutes = (total % 3_600) / 60
    val seconds = total % 60

    val hh = hours.toString().padStart(2, '0')
    val mm = minutes.toString().padStart(2, '0')
    val ss = seconds.toString().padStart(2, '0')

    return buildString {
        if (days > 0) append("${days}d ")
        append("$hh:$mm:$ss")
    }
}