package com.spoonofcode.poa.core.model.old

import kotlinx.serialization.Serializable

@Serializable
data class AddUserToSportEventRequest(
    val userId: Int,
)