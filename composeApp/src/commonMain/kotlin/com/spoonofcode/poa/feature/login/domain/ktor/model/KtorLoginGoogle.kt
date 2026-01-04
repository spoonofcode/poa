package com.spoonofcode.poa.feature.login.domain.ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class KtorLoginGoogle(
    val userId: String,
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)