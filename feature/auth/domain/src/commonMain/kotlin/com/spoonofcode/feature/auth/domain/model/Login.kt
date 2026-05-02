package com.spoonofcode.feature.auth.domain.model

data class Login(
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
    val id: String,
    val email: String,
)