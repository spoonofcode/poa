package com.spoonofcode.poa.core.ui.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.toLocalDateTime

object LocalDateTimeUtils {
    fun now() = Clock.System.now().toLocalDateTime(TimeZoneUtils.DEFAULT_ZONE)
}