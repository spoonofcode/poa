package com.spoonofcode.poa.feature.profile.domain.usecase

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.feature.profile.data.model.ProfileRequest

class UpdateProfileUseCase(
    private val userRepository: UserRepository,
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