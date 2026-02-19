package com.spoonofcode.feature.auth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
    val email: String,
)