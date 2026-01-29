package com.spoonofcode.feature.profile.domain.repository

import com.spoonofcode.feature.profile.domain.model.Profile

interface ProfileRepository {
    suspend fun readUserProfile(): Result<Profile>
    suspend fun updateUserProfile(): Result<Unit>
}