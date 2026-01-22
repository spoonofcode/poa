package com.spoonofcode.poa.core.network

import kotlinx.serialization.Serializable

@Serializable
data class RefreshRequest(
    val refreshToken: String
)