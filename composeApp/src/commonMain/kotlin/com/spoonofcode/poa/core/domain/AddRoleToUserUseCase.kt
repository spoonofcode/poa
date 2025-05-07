package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.UserRepository

class AddRoleToUserUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(
        roleId: Int,
        userId: Int,
    ) {
        userRepository.addRoleToUser(
            roleId = roleId,
            userId = userId,
        )
    }
}