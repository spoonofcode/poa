package com.spoonofcode.poa.feature.partner.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PartnerCategory(
    val id: String,
    val name: String,
)