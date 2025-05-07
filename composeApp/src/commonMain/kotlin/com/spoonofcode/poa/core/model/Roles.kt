package com.spoonofcode.poa.core.model

import kotlinx.serialization.Serializable

@Serializable
enum class Roles(val id: Int) {
    ADMIN(id = 1),
    CLUB_OWNER(id = 2),
    COACH(id = 3),
    USER(id = 4),
}