package com.spoonofcode.feature.profile.data.test

import com.spoonofcode.feature.profile.domain.model.Profile

object ProfileMockData {
    val PROFILE_1 = Profile(
        name = "Profile name 1",
        numberOfProductsOwnedByUser = 1,
        customLink = "https://example.com",
    )
}