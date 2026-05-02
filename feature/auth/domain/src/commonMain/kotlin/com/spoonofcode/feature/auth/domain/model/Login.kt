package com.spoonofcode.feature.auth.domain.model

data class Login(
    val accessToken: String,
    val refreshToken: String,
    val id: String,
    val email: String,
)