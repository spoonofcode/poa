package com.spoonofcode.poa.feature.partner.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PartnerCategoryResponse(
    val id: String,
    val name: String,
)