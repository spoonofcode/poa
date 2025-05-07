package com.spoonofcode.poa.core.ui.utils

import kotlinx.datetime.TimeZone

object TimeZoneUtils {
    private val WARSAW_ZONE = TimeZone.of("Europe/Warsaw")
    val DEFAULT_ZONE = WARSAW_ZONE
}