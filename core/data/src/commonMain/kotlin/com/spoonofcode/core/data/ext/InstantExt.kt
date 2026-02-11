package com.spoonofcode.core.data.ext

import com.spoonofcode.core.data.utils.TimeZoneUtils.DEFAULT_ZONE
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun Instant.toZonedLocalDateTime(): LocalDateTime = this.toLocalDateTime(DEFAULT_ZONE)
