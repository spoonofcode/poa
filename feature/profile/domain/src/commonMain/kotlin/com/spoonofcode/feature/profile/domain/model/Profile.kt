package com.spoonofcode.feature.profile.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: String,
    val email: String,
    val roles: List<String>,
)