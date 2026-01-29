package com.spoonofcode.feature.profile.domain.usecase

import com.spoonofcode.feature.profile.domain.repository.ProfileRepository

class UpdateProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    // TODO #109 Fix after migration to feature modules
//    suspend operator fun invoke(userId: String, profileRequest: ProfileRequest): Boolean {
//
//
//        return true
//
////        return userRepository.update(
////            id = userId,
////            request = profileRequest,
////        )
//    }
}