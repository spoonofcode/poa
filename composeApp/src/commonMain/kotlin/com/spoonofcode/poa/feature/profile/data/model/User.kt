package com.spoonofcode.poa.feature.profile.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val nickName: String? = null,
    val fullName: String = buildString {
        append("$firstName $lastName")
        nickName?.let { append(" ($it)") }
    },
    val email: String,
    val points: Int = 0,
)