package com.spoonofcode.poa.core.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class DailyCheckInStatus(
    val currentStreak: Int,
    val nextAvailableAt: LocalDateTime,
)