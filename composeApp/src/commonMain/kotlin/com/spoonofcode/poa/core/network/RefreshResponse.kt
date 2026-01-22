package com.spoonofcode.poa.core.network

import kotlinx.serialization.Serializable

@Serializable
data class RefreshResponse(
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)