package com.spoonofcode.poa.feature.profile.domain.usecase

import com.spoonofcode.poa.feature.profile.domain.model.Profile
import com.spoonofcode.poa.feature.profile.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(): Result<Profile> = profileRepository.readUserProfile()
}