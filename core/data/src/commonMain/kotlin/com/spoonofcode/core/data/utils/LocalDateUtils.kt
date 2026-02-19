package com.spoonofcode.core.data.utils


object LocalDateUtils {
    fun today() = LocalDateTimeUtils.now().date
}