package com.spoonofcode.core.data.ext

import com.spoonofcode.core.data.utils.TimeZoneUtils.DEFAULT_ZONE
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun Instant.toZonedLocalDate(): LocalDate = this.toLocalDateTime(DEFAULT_ZONE).date

fun Instant.toZonedLocalDateTime(): LocalDateTime = this.toLocalDateTime(DEFAULT_ZONE)
