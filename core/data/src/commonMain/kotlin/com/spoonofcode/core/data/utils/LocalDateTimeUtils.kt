package com.spoonofcode.core.data.utils

import com.spoonofcode.core.data.ext.toZonedLocalDateTime
import kotlin.time.Clock

object LocalDateTimeUtils {
    fun now() = Clock.System.now().toZonedLocalDateTime()
}