package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class PartnerCategoryRequest(
    val name: String,
)

@Serializable
data class PartnerCategory(
    val id: Int,
    val name: String,
)