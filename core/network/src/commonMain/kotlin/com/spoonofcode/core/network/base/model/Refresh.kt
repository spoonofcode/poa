package com.spoonofcode.core.network.base.model

import kotlinx.serialization.Serializable

@Serializable
data class Refresh(
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)