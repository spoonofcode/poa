package com.spoonofcode.poa.feature.login.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Refresh(
    val jwtAccessToken: String,
    val jwtRefreshToken: String,
)