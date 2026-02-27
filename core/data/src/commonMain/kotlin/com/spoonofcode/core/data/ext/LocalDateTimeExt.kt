package com.spoonofcode.core.data.ext

import com.spoonofcode.core.data.utils.LocalDateUtils
import com.spoonofcode.core.data.utils.TimeZoneUtils
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.hours
import kotlin.time.Instant

private val DEFAULT_DATE_TIME_FORMAT = LocalDateTime.Format {
    date(
        LocalDate.Format {
            dayOfMonth()
            char(' ')
            monthName(MonthNames.ENGLISH_ABBREVIATED)
            char(' ')
            year()
        }
    )
    char(' ')
    time(
        LocalTime.Format {
            hour(); char(':'); minute()
        }
    )
}
private val DEFAULT_TIME_FORMAT = LocalDateTime.Format {
    time(
        LocalTime.Format {
            hour(); char(':'); minute()
        }
    )
}


fun LocalDateTime.formatedLocalDateTime(): String = this.format(DEFAULT_DATE_TIME_FORMAT)

fun LocalDateTime.formatedTime(): String = this.format(DEFAULT_TIME_FORMAT)

fun LocalDateTime.isAfterToday(): Boolean {
    return this.date > LocalDateUtils.today()
}

fun LocalDateTime.plus(hours: Int): LocalDateTime =
    this.toInstant(TimeZoneUtils.DEFAULT_ZONE)
        .plus(hours.hours)
        .toLocalDateTime(TimeZoneUtils.DEFAULT_ZONE)

fun LocalDateTime.minus(hours: Int): LocalDateTime =
    this.toInstant(TimeZoneUtils.DEFAULT_ZONE)
        .minus(hours.hours)
        .toLocalDateTime(TimeZoneUtils.DEFAULT_ZONE)

fun LocalDateTime.roundToNextHour(): LocalDateTime =
    LocalDateTime(this.year, this.monthNumber, this.dayOfMonth, this.plus(1).hour, 0, 0, 0)


fun LocalDateTime.toZonedInstant(): Instant = this.toInstant(TimeZoneUtils.DEFAULT_ZONE)

fun LocalDateTime.toMillis(): Long = this.toInstant(TimeZoneUtils.DEFAULT_ZONE).toEpochMilliseconds()