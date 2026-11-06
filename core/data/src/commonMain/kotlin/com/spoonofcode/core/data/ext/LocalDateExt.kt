package com.spoonofcode.core.data.ext

import com.spoonofcode.core.data.utils.TimeZoneUtils
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.plus
import kotlin.time.Instant

val DEFAULT_DATE_FORMAT =
    LocalDate.Format {
        date(
            LocalDate.Format {
                dayOfMonth()
                char('-')
                monthNumber()
                char('-')
                year()
            },
        )
    }

fun LocalDate.formatedLocalDate(): String = this.format(DEFAULT_DATE_FORMAT)

fun LocalDate.zonedStartOfDayIn(): Instant = this.atStartOfDayIn(TimeZoneUtils.DEFAULT_ZONE)

fun LocalDate.zonedStartOfDayInMillis(): Long = this.zonedStartOfDayIn().toEpochMilliseconds()

fun LocalDate.zonedEndOfDayInMillis(): Long = this.plus(1, DateTimeUnit.DAY).zonedStartOfDayInMillis() - 1
