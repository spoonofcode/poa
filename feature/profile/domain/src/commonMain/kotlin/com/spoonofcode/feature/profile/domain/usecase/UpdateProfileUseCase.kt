package com.spoonofcode.feature.profile.domain.usecase

import com.spoonofcode.feature.profile.domain.repository.ProfileRepository

class UpdateProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(): Result<Unit> = profileRepository.updateUserProfile()
}