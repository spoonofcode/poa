package com.spoonofcode.feature.profile.domain.model

data class Profile(
    val id: String,
    val email: String,
    val roles: List<String>,
)