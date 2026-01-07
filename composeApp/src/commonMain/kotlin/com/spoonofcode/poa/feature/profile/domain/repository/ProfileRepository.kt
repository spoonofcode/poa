package com.spoonofcode.poa.feature.profile.domain.repository

import com.spoonofcode.poa.feature.profile.domain.model.Profile

interface ProfileRepository {
    suspend fun readUserProfile(): Result<Profile>
}