package com.spoonofcode.feature.profile.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val id: String,
    val email: String,
    val roles: List<String>,
)