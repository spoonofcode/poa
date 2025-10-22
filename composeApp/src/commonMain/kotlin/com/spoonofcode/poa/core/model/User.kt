package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
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

@Serializable
data class UserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
)