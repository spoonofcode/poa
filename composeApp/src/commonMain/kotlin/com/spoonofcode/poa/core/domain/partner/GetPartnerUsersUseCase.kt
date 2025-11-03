package com.spoonofcode.poa.core.domain.partner

import com.spoonofcode.poa.core.data.repository.PartnerRepository
import com.spoonofcode.poa.core.model.User

class GetPartnerUsersUseCase(
    private val partnerRepository: PartnerRepository,
) {
    suspend operator fun invoke(partnerId: String): List<User> =
        partnerRepository.readPartnerUsers(partnerId = partnerId)
}

