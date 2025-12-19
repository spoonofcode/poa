package com.spoonofcode.poa.feature.product.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AddProductToUserRequest(
    val productId: String,
)