package com.spoonofcode.poa.feature.login.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginGoogle(
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)