package com.spoonofcode.feature.partner.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CustomerResponse(
    val id: String,
    val email: String,
    val name: String,
)