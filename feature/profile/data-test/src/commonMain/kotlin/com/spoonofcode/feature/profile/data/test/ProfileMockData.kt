package com.spoonofcode.feature.profile.data.test

import com.spoonofcode.feature.profile.domain.model.Profile

object ProfileMockData {
    val PROFILE_1 = Profile(
        id = "1",
        email = "exmaple.mail.1@gmail.com",
        roles = listOf("user", "partner"),
    )
}