package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.model.User

class GetUserByIdUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        userId: Int
    ): User = userRepository.read(id = userId)
}