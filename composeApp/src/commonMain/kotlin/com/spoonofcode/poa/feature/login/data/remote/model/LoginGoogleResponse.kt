package com.spoonofcode.poa.feature.login.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginGoogleResponse(
    val userId: String,
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)