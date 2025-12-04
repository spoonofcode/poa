package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Register(
    val userId: String,
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)