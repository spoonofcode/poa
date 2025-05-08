package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.UserRepository
import com.spoonofcode.poa.core.model.User

class GetAllUsersUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): List<User> = userRepository.readAll()
}