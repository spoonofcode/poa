package com.spoonofcode.poa.feature.product.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AddProductToUserRequest(
    val productId: String,
)