package com.spoonofcode.feature.profile.data.mappers

import com.spoonofcode.feature.profile.data.model.ProfileResponse
import com.spoonofcode.poa.feature.profile.domain.model.Profile

fun ProfileResponse.toProfile(): Profile {
    return Profile(
        name = name,
        numberOfProductsOwnedByUser = numberOfProductsOwnedByUser,
        customLink = customLink,
    )
}