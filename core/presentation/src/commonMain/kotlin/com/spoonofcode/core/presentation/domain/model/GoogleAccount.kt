package com.spoonofcode.core.presentation.domain.model

// TODO #109 Fix after migration to feature modules
data class GoogleAccount(
    val idToken: String,
    val displayName: String = "",
    val profileImageUrl: String? = null
)