package com.spoonofcode.feature.profile.domain.model

import kotlinx.datetime.LocalDateTime

data class Profile(
    val id: String,
    val email: String,
    val roles: List<String>,
    val partnerExpiresAt: LocalDateTime? = null,
    val partnerStatus: String? = null,
)