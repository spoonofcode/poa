package com.spoonofcode.feature.profile.data.mappers

import com.spoonofcode.feature.profile.data.model.ProfileResponse
import com.spoonofcode.feature.profile.domain.model.Profile

fun ProfileResponse.toProfile(): Profile {
    return Profile(
        id = id,
        email = email,
        roles = roles,
    )
}