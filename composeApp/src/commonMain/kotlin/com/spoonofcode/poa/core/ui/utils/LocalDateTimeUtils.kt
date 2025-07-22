package com.spoonofcode.poa.core.ui.utils

import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

object LocalDateTimeUtils {
    @OptIn(ExperimentalTime::class)
    fun now() = Clock.System.now().toLocalDateTime(TimeZoneUtils.DEFAULT_ZONE)
}