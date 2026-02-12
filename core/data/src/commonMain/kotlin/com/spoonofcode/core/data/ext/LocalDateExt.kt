package com.spoonofcode.core.data.ext

import com.spoonofcode.core.data.utils.TimeZoneUtils
import kotlinx.datetime.LocalDate
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlin.time.Instant

val DEFAULT_DATE_FORMAT = LocalDate.Format {
    date(
        LocalDate.Format {
            dayOfMonth()
            char('-')
            monthNumber()
            char('-')
            year()
        }
    )
}

fun LocalDate.formatedLocalDate(): String = this.format(DEFAULT_DATE_FORMAT)

fun LocalDate.zonedStartOfDayIn(): Instant = this.atStartOfDayIn(TimeZoneUtils.DEFAULT_ZONE)

fun LocalDate.zonedStartOfDayInMillis(): Long = this.zonedStartOfDayIn().toEpochMilliseconds()