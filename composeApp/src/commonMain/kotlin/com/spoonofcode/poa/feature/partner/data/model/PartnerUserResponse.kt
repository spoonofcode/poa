package com.spoonofcode.poa.feature.partner.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PartnerUserResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val nickName: String? = null,
)