package com.spoonofcode.poa.core.model.old

import kotlinx.serialization.Serializable

@Serializable
data class Blockchain(
    val id: String,
    val fullName: String,
)