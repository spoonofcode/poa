package com.spoonofcode.feature.partner.domain.model

data class PartnerUser(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val nickName: String? = null,
    val fullName: String = buildString {
        append("$firstName $lastName")
        nickName?.let { append(" ($it)") }
    },
)