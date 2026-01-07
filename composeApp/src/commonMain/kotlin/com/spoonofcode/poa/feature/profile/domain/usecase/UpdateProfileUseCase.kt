package com.spoonofcode.poa.feature.profile.domain.usecase

import com.spoonofcode.poa.feature.profile.data.model.ProfileRequest
import com.spoonofcode.poa.feature.profile.domain.repository.ProfileRepository

class UpdateProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(userId: String, profileRequest: ProfileRequest): Boolean {
        // TODO FIX this

        return true

//        return userRepository.update(
//            id = userId,
//            request = profileRequest,
//        )
    }
}