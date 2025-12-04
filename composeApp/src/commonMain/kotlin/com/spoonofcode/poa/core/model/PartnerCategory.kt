package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class PartnerCategory(
    val id: String,
    val name: String,
)