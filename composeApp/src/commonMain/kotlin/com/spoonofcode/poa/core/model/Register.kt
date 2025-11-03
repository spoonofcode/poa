package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Register(
    val userId: String,
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
)