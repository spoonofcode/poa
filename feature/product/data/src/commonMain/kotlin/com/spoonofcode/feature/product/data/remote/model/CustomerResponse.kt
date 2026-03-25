package com.spoonofcode.feature.product.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CustomerResponse(
    val id: String? = null,
    val email: String,
    val name: String,
)