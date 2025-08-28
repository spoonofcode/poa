package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class AddProductUserRequest(
    val productId: Int,
)