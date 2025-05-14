package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Blockchain(
    val id: String,
    val fullName: String,
)