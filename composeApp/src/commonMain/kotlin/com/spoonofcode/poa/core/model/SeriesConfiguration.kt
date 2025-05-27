package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SeriesConfiguration(
    val name: String,
    val fullName: String,
    val enabled: String,
    val blokchain: String,
    val owner: String,
)