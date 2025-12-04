package com.spoonofcode.poa.core.domain.user

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.model.User

class GetUserByIdUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        userId: String
    ): Result<User> = userRepository.read(userId = userId)
}