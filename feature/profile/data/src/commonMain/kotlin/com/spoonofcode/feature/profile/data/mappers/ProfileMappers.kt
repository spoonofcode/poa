package com.spoonofcode.feature.profile.data.mappers

import com.spoonofcode.core.data.ext.toZonedLocalDateTime
import com.spoonofcode.feature.profile.data.model.ProfileResponse
import com.spoonofcode.feature.profile.domain.model.Profile

fun ProfileResponse.toProfile(): Profile = Profile(
    id = id,
    email = email,
    roles = roles,
    partnerExpiresAt = partnerExpiresAt?.toZonedLocalDateTime(),
    partnerStatus = partnerStatus,
)