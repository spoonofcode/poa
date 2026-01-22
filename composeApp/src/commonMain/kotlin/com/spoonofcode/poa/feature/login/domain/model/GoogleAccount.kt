package com.spoonofcode.poa.feature.login.domain.model

data class GoogleAccount(
    val idToken: String,
    val displayName: String = "",
    val profileImageUrl: String? = null
)