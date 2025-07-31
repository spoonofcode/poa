package com.spoonofcode.poa.core.model.old

import kotlinx.serialization.Serializable

@Serializable
data class NFTSerie(
    val id: String,
    val blockchain: String,
    val enabled: Boolean,
    val fullName: String,
)