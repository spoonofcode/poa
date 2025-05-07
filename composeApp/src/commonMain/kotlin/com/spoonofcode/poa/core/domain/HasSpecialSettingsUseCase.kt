package com.spoonofcode.poa.core.domain

import com.spoonofcode.poa.core.data.repository.RoleRepository
import com.spoonofcode.poa.core.model.Roles
import com.spoonofcode.poa.core.network.SessionManager

class HasSpecialSettingsUseCase(
    private val roleRepository: RoleRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): Boolean {
        val roles = roleRepository.readAllRolesByUserId(
            userId = sessionManager.getSessionUserId()!!
        )

        return roles.any { it.id == Roles.ADMIN.id || it.id == Roles.CLUB_OWNER.id }
    }
}