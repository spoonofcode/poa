package com.spoonofcode.poa.feature.login.data.remote.ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class KtorLoginGoogleResponse(
    val userId: String,
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)