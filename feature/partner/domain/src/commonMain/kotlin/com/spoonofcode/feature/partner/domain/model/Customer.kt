package com.spoonofcode.feature.partner.domain.model

data class Customer(
    // TODO tutaj nie powinno być nulla
    val id: String? = null,
    val email: String,
    val name: String,
)