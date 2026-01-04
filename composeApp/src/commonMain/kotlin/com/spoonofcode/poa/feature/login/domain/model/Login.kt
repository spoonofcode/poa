package com.spoonofcode.poa.feature.login.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val userId: String,
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)