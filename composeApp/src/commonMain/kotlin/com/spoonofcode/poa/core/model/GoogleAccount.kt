package com.spoonofcode.poa.core.model

data class GoogleAccount(
    val token: String,
    val displayName: String = "",
    val profileImageUrl: String? = null
)