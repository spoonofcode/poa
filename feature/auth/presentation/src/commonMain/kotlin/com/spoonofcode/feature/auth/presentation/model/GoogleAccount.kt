package com.spoonofcode.feature.auth.presentation.model

data class GoogleAccount(
    val idToken: String,
    val displayName: String = "",
    val profileImageUrl: String? = null,
)
