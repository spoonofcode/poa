package com.spoonofcode.poa.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AddProductToUserRequest(
    val productId: String,
)