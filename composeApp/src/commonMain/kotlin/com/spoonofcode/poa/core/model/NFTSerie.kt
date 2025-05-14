package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
data class NFTSerie(
    val id: String,
    val blockchain: String,
    val enabled: Boolean,
    val fullName: String,
)