package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val userId: Int,
    val userEmail: String,
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String,
)