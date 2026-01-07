package com.spoonofcode.poa.feature.login.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)